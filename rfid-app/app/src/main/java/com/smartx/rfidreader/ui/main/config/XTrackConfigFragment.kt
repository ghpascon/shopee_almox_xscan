package com.smartx.rfidreader.ui.main.config

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.smartx.rfidreader.R
import com.smartx.rfidreader.core.settings.AppSettings
import com.smartx.rfidreader.core.xtrack.XTrackDevice
import com.smartx.rfidreader.core.xtrack.XTrackLocation
import com.smartx.rfidreader.core.xtrack.xtrack_manager
import com.smartx.rfidreader.databinding.FragmentXtrackConfigBinding
import com.smartx.rfidreader.ui.main.MainViewModel
import java.util.Locale
import kotlinx.coroutines.launch

class XTrackConfigFragment : Fragment() {

    private var _binding: FragmentXtrackConfigBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    private var locationOptions: List<LocationOption> = emptyList()
    private var deviceOptions: List<DeviceOption> = emptyList()
    private var lastLoadedLocationsUrl: String = ""
    private var lastLoadedDevicesUrl: String = ""
    private var lastLoadedDeviceDetailsKey: String = ""
    private var lastTechnicalDetails: String = ""
    private var isLoadingSelectedDeviceDetails: Boolean = false

    private data class LocationOption(
        val id: String,
        val name: String
    ) {
        override fun toString(): String {
            return if (name.isBlank()) id else "$name ($id)"
        }
    }

    private data class DeviceOption(
        val id: String,
        val name: String,
        val uid: String
    ) {
        override fun toString(): String = uid.trim()
    }

    private data class ParsedDeviceUidSettings(
        val prefixes: List<String>?,
        val epcLengths: List<Int>?,
        val errors: List<String>
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentXtrackConfigBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textXtrackDetails.text = getString(R.string.xtrack_details_placeholder)
        setupActions()
        observeState()
    }

    private fun setupActions() {
        binding.btnReloadLocations.setOnClickListener {
            val url = saveUrlAndReturn(clearLocationIfUrlChanged = true)
            if (url.isBlank()) {
                showSnackbar(getString(R.string.error_xtrack_url_required))
                return@setOnClickListener
            }
            loadAvailableLocations(url)
            loadAvailableDevices(url)
        }

        binding.btnSaveXtrack.setOnClickListener {
            val url = saveUrlAndReturn(clearLocationIfUrlChanged = true)
            if (url.isBlank()) {
                showSnackbar(getString(R.string.error_xtrack_url_required))
                return@setOnClickListener
            }
            if (locationOptions.isEmpty()) {
                loadAvailableLocations(url)
            }
            if (deviceOptions.isEmpty()) {
                loadAvailableDevices(url)
            }
            showSnackbar(getString(R.string.app_settings_saved))
        }

        binding.autoDevice.setOnClickListener {
            showDeviceSearchDialog()
        }
        binding.layoutDevice.setOnClickListener {
            showDeviceSearchDialog()
        }

        binding.autoLocationEntry.setOnClickListener {
            showLocationSearchDialog(isEntry = true)
        }
        binding.layoutLocation.setOnClickListener {
            showLocationSearchDialog(isEntry = true)
        }

        binding.autoLocationExit.setOnClickListener {
            showLocationSearchDialog(isEntry = false)
        }
        binding.layoutLocationExit.setOnClickListener {
            showLocationSearchDialog(isEntry = false)
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    updateFromSettings(state.appSettings)
                }
            }
        }
    }

    private fun updateFromSettings(settings: AppSettings) {
        val currentXtrack = binding.editXtrackUrl.text?.toString().orEmpty()
        if (currentXtrack != settings.xtrackUrl) {
            binding.editXtrackUrl.setText(settings.xtrackUrl)
        }

        maybeReloadLocations(settings.xtrackUrl)
        maybeReloadDevices(settings.xtrackUrl)
        syncSelectedLocationText(settings)
        syncSelectedDeviceText(settings)
        maybeReloadSelectedDeviceDetails(settings)
    }

    private fun saveUrlAndReturn(clearLocationIfUrlChanged: Boolean): String {
        val normalizedUrl = binding.editXtrackUrl.text?.toString()?.trim().orEmpty()
        val current = viewModel.uiState.value.appSettings
        val urlChanged = current.xtrackUrl.trim() != normalizedUrl

        val updated = if (clearLocationIfUrlChanged && urlChanged) {
            current.copy(
                xtrackUrl = normalizedUrl,
                xtrackDeviceId = "",
                xtrackDeviceName = "",
                xtrackDeviceUid = "",
                entryLocationId = "",
                entryLocationName = "",
                exitLocationId = "",
                exitLocationName = ""
            )
        } else {
            current.copy(xtrackUrl = normalizedUrl)
        }

        viewModel.saveAppSettings(updated)

        if (clearLocationIfUrlChanged && urlChanged) {
            locationOptions = emptyList()
            deviceOptions = emptyList()
            binding.autoLocationEntry.setText("", false)
            binding.autoLocationExit.setText("", false)
            binding.autoDevice.setText("", false)
            lastLoadedLocationsUrl = ""
            lastLoadedDevicesUrl = ""
            lastLoadedDeviceDetailsKey = ""
        }

        return normalizedUrl
    }

    private fun maybeReloadLocations(xtrackUrl: String) {
        val normalized = xtrackUrl.trim()
        if (normalized.isBlank()) return
        if (normalized == lastLoadedLocationsUrl) return
        loadAvailableLocations(normalized)
    }

    private fun maybeReloadDevices(xtrackUrl: String) {
        val normalized = xtrackUrl.trim()
        if (normalized.isBlank()) return
        if (normalized == lastLoadedDevicesUrl) return
        loadAvailableDevices(normalized)
    }

    private fun loadAvailableLocations(xtrackUrl: String) {
        val normalized = xtrackUrl.trim()
        if (normalized.isBlank()) return

        lastLoadedLocationsUrl = normalized
        binding.layoutLocation.isEnabled = false
        binding.layoutLocationExit.isEnabled = false
        binding.btnReloadLocations.isEnabled = false
        binding.progressLocations.visibility = View.VISIBLE
        setTechnicalDetails(getString(R.string.xtrack_details_loading, normalized))

        viewLifecycleOwner.lifecycleScope.launch {
            val result = xtrack_manager.fetchLocations(normalized)
            if (_binding == null) return@launch

            binding.layoutLocation.isEnabled = true
            binding.layoutLocationExit.isEnabled = true
            binding.btnReloadLocations.isEnabled = true
            binding.progressLocations.visibility = View.GONE

            result.onSuccess { locations ->
                applyLocationOptions(locations)
                syncSelectedLocationText(viewModel.uiState.value.appSettings)

                val sample = locations.firstOrNull()?.let {
                    if (it.name.isBlank()) it.id else "${it.name} (${it.id})"
                } ?: "-"
                setTechnicalDetails(
                    getString(R.string.xtrack_details_success, locations.size, sample)
                )
            }.onFailure { err ->
                applyLocationOptions(emptyList())
                setTechnicalDetails(
                    getString(
                        R.string.xtrack_details_error,
                        err.message ?: err::class.java.simpleName
                    )
                )
                showSnackbar(getString(R.string.error_load_locations, err.message ?: "erro"))
            }
        }
    }

    private fun loadAvailableDevices(xtrackUrl: String) {
        val normalized = xtrackUrl.trim()
        if (normalized.isBlank()) return

        lastLoadedDevicesUrl = normalized
        binding.layoutDevice.isEnabled = false
        binding.btnReloadLocations.isEnabled = false
        binding.progressLocations.visibility = View.VISIBLE

        viewLifecycleOwner.lifecycleScope.launch {
            val result = xtrack_manager.fetchDevices(normalized)
            if (_binding == null) return@launch

            binding.layoutDevice.isEnabled = true
            binding.btnReloadLocations.isEnabled = true
            binding.progressLocations.visibility = View.GONE

            result.onSuccess { devices ->
                applyDeviceOptions(devices)
                val settings = viewModel.uiState.value.appSettings
                val selectedUid = normalizeDeviceUid(settings.xtrackDeviceUid)
                val selectedName = settings.xtrackDeviceName.trim()

                if (selectedUid.isBlank() && selectedName.isNotBlank()) {
                    val migrated = deviceOptions.firstOrNull {
                        it.name.equals(selectedName, ignoreCase = true)
                    }
                    if (migrated != null) {
                        saveSelectedDevice(migrated, showSavedMessage = false)
                    } else {
                        syncSelectedDeviceText(settings)
                    }
                } else if (selectedUid.isBlank()) {
                    val defaultOption = deviceOptions.firstOrNull {
                        it.uid.equals("COLETOR_ALMOX", ignoreCase = true)
                    }
                    if (defaultOption != null) {
                        saveSelectedDevice(defaultOption, showSavedMessage = false)
                    } else {
                        syncSelectedDeviceText(settings)
                    }
                } else {
                    syncSelectedDeviceText(settings)
                }
            }.onFailure { err ->
                applyDeviceOptions(emptyList())
                showSnackbar(getString(R.string.error_load_devices, err.message ?: "erro"))
            }
        }
    }

    private fun setTechnicalDetails(message: String) {
        lastTechnicalDetails = message
        if (_binding != null) {
            binding.textXtrackDetails.text = lastTechnicalDetails
        }
    }

    private fun applyLocationOptions(locations: List<XTrackLocation>) {
        locationOptions = locations.map { LocationOption(id = it.id, name = it.name) }
    }

    private fun applyDeviceOptions(devices: List<XTrackDevice>) {
        deviceOptions = devices
            .filter { it.active }
            .mapNotNull { device ->
                val normalizedName = device.name.trim()
                val normalizedUid = device.uid.trim()
                if (normalizedName.isBlank() || normalizedUid.isBlank()) return@mapNotNull null
                DeviceOption(
                    id = device.id,
                    name = normalizedName,
                    uid = normalizedUid
                )
            }
            .distinctBy {
                val uidKey = it.uid.trim().lowercase(Locale.ROOT)
                if (uidKey.isNotBlank()) uidKey else it.name.lowercase(Locale.ROOT)
            }
    }

    private fun filterLocationOptions(query: String): List<LocationOption> {
        val normalized = query.trim().lowercase(Locale.ROOT)
        if (normalized.isBlank()) return locationOptions

        return locationOptions.filter { option ->
            option.id.lowercase(Locale.ROOT).contains(normalized) ||
                option.name.lowercase(Locale.ROOT).contains(normalized) ||
                option.toString().lowercase(Locale.ROOT).contains(normalized)
        }
    }

    private fun showLocationSearchDialog(isEntry: Boolean) {
        if (locationOptions.isEmpty()) {
            showSnackbar(getString(R.string.error_load_locations, "nenhuma localização carregada"))
            return
        }

        val dialogView = layoutInflater.inflate(R.layout.dialog_location_search, null)
        val searchInput = dialogView.findViewById<TextInputEditText>(R.id.editDialogSearchLocation)
        val listView = dialogView.findViewById<ListView>(R.id.listDialogLocations)

        var filtered = locationOptions
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, filtered.toMutableList())
        listView.adapter = adapter

        searchInput.addTextChangedListener { text ->
            filtered = filterLocationOptions(text?.toString().orEmpty())
            adapter.clear()
            adapter.addAll(filtered)
            adapter.notifyDataSetChanged()
        }

        val titleRes = if (isEntry) R.string.label_location_entry else R.string.label_location_exit

        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle(titleRes)
            .setView(dialogView)
            .setNegativeButton(R.string.btn_cancel, null)
            .create()

        listView.setOnItemClickListener { _, _, position, _ ->
            val option = filtered.getOrNull(position) ?: return@setOnItemClickListener
            saveSelectedLocation(option, isEntry)
            dialog.dismiss()
        }

        dialog.setOnShowListener {
            searchInput.requestFocus()
            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            searchInput.post {
                imm?.showSoftInput(searchInput, InputMethodManager.SHOW_IMPLICIT)
            }
        }

        dialog.setOnDismissListener {
            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(searchInput.windowToken, 0)
        }

        dialog.show()
    }

    private fun showDeviceSearchDialog() {
        if (deviceOptions.isEmpty()) {
            showSnackbar(getString(R.string.error_load_devices, "nenhum device carregado"))
            return
        }

        val dialogView = layoutInflater.inflate(R.layout.dialog_location_search, null)
        val searchInput = dialogView.findViewById<TextInputEditText>(R.id.editDialogSearchLocation)
        val listView = dialogView.findViewById<ListView>(R.id.listDialogLocations)

        searchInput.hint = getString(R.string.hint_search_device)

        var filtered = deviceOptions
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, filtered.map { it.toString() }.toMutableList())
        listView.adapter = adapter

        searchInput.addTextChangedListener { text ->
            filtered = filterDeviceOptions(text?.toString().orEmpty())
            adapter.clear()
            adapter.addAll(filtered.map { it.toString() })
            adapter.notifyDataSetChanged()
        }

        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.label_device)
            .setView(dialogView)
            .setNegativeButton(R.string.btn_cancel, null)
            .create()

        listView.setOnItemClickListener { _, _, position, _ ->
            val option = filtered.getOrNull(position) ?: return@setOnItemClickListener
            saveSelectedDevice(option)
            dialog.dismiss()
        }

        dialog.setOnShowListener {
            searchInput.requestFocus()
            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            searchInput.post {
                imm?.showSoftInput(searchInput, InputMethodManager.SHOW_IMPLICIT)
            }
        }

        dialog.setOnDismissListener {
            val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(searchInput.windowToken, 0)
        }

        dialog.show()
    }

    private fun filterDeviceOptions(query: String): List<DeviceOption> {
        val normalized = query.trim().lowercase(Locale.ROOT)
        if (normalized.isBlank()) return deviceOptions

        return deviceOptions.filter { option ->
            option.name.lowercase(Locale.ROOT).contains(normalized) ||
                option.id.lowercase(Locale.ROOT).contains(normalized) ||
                option.uid.lowercase(Locale.ROOT).contains(normalized)
        }
    }

    private fun saveSelectedLocation(option: LocationOption, isEntry: Boolean) {
        val current = viewModel.uiState.value.appSettings
        val normalizedUrl = binding.editXtrackUrl.text?.toString()?.trim().orEmpty()

        val updatedSettings = if (isEntry) {
            current.copy(
                xtrackUrl = normalizedUrl,
                entryLocationId = option.id,
                entryLocationName = option.name
            )
        } else {
            current.copy(
                xtrackUrl = normalizedUrl,
                exitLocationId = option.id,
                exitLocationName = option.name
            )
        }

        viewModel.saveAppSettings(updatedSettings)
        showSnackbar(getString(R.string.app_settings_saved))
    }

    private fun saveSelectedDevice(option: DeviceOption, showSavedMessage: Boolean = true) {
        val current = viewModel.uiState.value.appSettings
        val normalizedUrl = binding.editXtrackUrl.text?.toString()?.trim().orEmpty()
        val updatedSettings = current.copy(
            xtrackUrl = normalizedUrl,
            xtrackDeviceId = option.id,
            xtrackDeviceName = option.name,
            xtrackDeviceUid = option.uid
        )

        viewModel.saveAppSettings(updatedSettings)
        if (showSavedMessage) {
            showSnackbar(getString(R.string.app_settings_saved))
        }
        loadSelectedDeviceDetails(normalizedUrl, option.uid, force = true)
    }

    private fun syncSelectedLocationText(settings: AppSettings) {
        val selectedEntry = locationOptions.firstOrNull { it.id == settings.entryLocationId }
        val entryDisplayValue = when {
            selectedEntry != null -> selectedEntry.toString()
            settings.entryLocationName.isNotBlank() && settings.entryLocationId.isNotBlank() ->
                "${settings.entryLocationName} (${settings.entryLocationId})"
            settings.entryLocationName.isNotBlank() -> settings.entryLocationName
            settings.entryLocationId.isNotBlank() -> settings.entryLocationId
            else -> ""
        }

        val selectedExit = locationOptions.firstOrNull { it.id == settings.exitLocationId }
        val exitDisplayValue = when {
            selectedExit != null -> selectedExit.toString()
            settings.exitLocationName.isNotBlank() && settings.exitLocationId.isNotBlank() ->
                "${settings.exitLocationName} (${settings.exitLocationId})"
            settings.exitLocationName.isNotBlank() -> settings.exitLocationName
            settings.exitLocationId.isNotBlank() -> settings.exitLocationId
            else -> ""
        }

        val currentEntryText = binding.autoLocationEntry.text?.toString().orEmpty()
        if (currentEntryText != entryDisplayValue) {
            binding.autoLocationEntry.setText(entryDisplayValue, false)
        }

        val currentExitText = binding.autoLocationExit.text?.toString().orEmpty()
        if (currentExitText != exitDisplayValue) {
            binding.autoLocationExit.setText(exitDisplayValue, false)
        }
    }

    private fun syncSelectedDeviceText(settings: AppSettings) {
        val selectedDeviceUid = settings.xtrackDeviceUid.trim()
        val selectedDeviceName = settings.xtrackDeviceName.trim()
        val selected = deviceOptions.firstOrNull {
            (selectedDeviceUid.isNotBlank() && it.uid.equals(selectedDeviceUid, ignoreCase = true)) ||
                it.name.equals(selectedDeviceName, ignoreCase = true)
        }

        val displayValue = selected?.uid ?: selectedDeviceUid
        val currentText = binding.autoDevice.text?.toString().orEmpty()
        if (currentText != displayValue) {
            binding.autoDevice.setText(displayValue, false)
        }
    }

    private fun maybeReloadSelectedDeviceDetails(settings: AppSettings) {
        val url = settings.xtrackUrl.trim()
        val selectedUid = normalizeDeviceUid(settings.xtrackDeviceUid)
        if (url.isBlank() || selectedUid.isBlank()) return

        val key = "$url|$selectedUid"
        if (key == lastLoadedDeviceDetailsKey) return

        loadSelectedDeviceDetails(url, selectedUid)
    }

    private fun loadSelectedDeviceDetails(xtrackUrl: String, selectedUidRaw: String, force: Boolean = false) {
        val normalizedUrl = xtrackUrl.trim()
        val normalizedUid = normalizeDeviceUid(selectedUidRaw)
        if (normalizedUrl.isBlank() || normalizedUid.isBlank()) return

        val key = "$normalizedUrl|$normalizedUid"
        if (!force && key == lastLoadedDeviceDetailsKey) return
        if (isLoadingSelectedDeviceDetails && !force) return
        lastLoadedDeviceDetailsKey = key
        isLoadingSelectedDeviceDetails = true

        viewLifecycleOwner.lifecycleScope.launch {
            val result = xtrack_manager.fetchDevices(normalizedUrl)
            try {
                if (_binding == null) return@launch

                result.onSuccess { devices ->
                    runCatching {
                        val selectedDevice = devices.firstOrNull {
                            normalizeDeviceUid(it.uid) == normalizedUid
                        } ?: throw IllegalStateException("Device com UID selecionado não encontrado")

                        // Alguns ambientes retornam o payload de configuração em NAME.
                        val configPayload = selectedDevice.name.trim().ifBlank { selectedDevice.uid.trim() }
                        val parsed = parseDeviceUidSettings(configPayload)
                        val current = viewModel.uiState.value.appSettings

                        var updated = current.copy(
                            xtrackUrl = normalizedUrl,
                            xtrackDeviceId = selectedDevice.id,
                            xtrackDeviceName = selectedDevice.name,
                            xtrackDeviceUid = selectedDevice.uid.trim()
                        )

                        parsed.prefixes?.let { prefixes ->
                            updated = updated.copy(prefixes = prefixes.map { it.uppercase(Locale.ROOT) })
                        }
                        parsed.epcLengths?.let { epcLens ->
                            updated = updated.copy(epcLengths = epcLens)
                        }

                        if (updated != current) {
                            viewModel.saveAppSettings(updated)
                        }

                        if (parsed.errors.isNotEmpty()) {
                            showSnackbar(getString(R.string.xtrack_device_uid_invalid, parsed.errors.joinToString(" | ")))
                        } else {
                            showSnackbar(
                                getString(
                                    R.string.xtrack_device_applied,
                                    selectedDevice.uid,
                                    updated.prefixes.size,
                                    updated.epcLengths.size
                                )
                            )
                        }
                    }.onFailure { parseErr ->
                        lastLoadedDeviceDetailsKey = ""
                        showSnackbar(getString(R.string.xtrack_device_uid_invalid, parseErr.message ?: "erro ao processar UID"))
                    }
                }.onFailure { err ->
                    lastLoadedDeviceDetailsKey = ""
                    showSnackbar(getString(R.string.error_load_devices, err.message ?: "erro"))
                }
            } finally {
                isLoadingSelectedDeviceDetails = false
            }
        }
    }

    private fun normalizeDeviceUid(value: String): String {
        return value.trim().lowercase(Locale.ROOT)
    }

    private fun parseDeviceUidSettings(uidRaw: String): ParsedDeviceUidSettings {
        val errors = mutableListOf<String>()
        val uid = uidRaw.trim().lowercase(Locale.ROOT)
        if (uid.isBlank()) {
            return ParsedDeviceUidSettings(
                prefixes = null,
                epcLengths = null,
                errors = listOf("UID vazio")
            )
        }

        val payload = uid.removePrefix("{").removeSuffix("}")

        fun extractRawList(key: String): String? {
            val escapedKey = Regex.escape(key)
            val listRegex = Regex("""(?i)(?:^|[,{\s])["']?$escapedKey["']?\s*[:=]\s*\[([^\]]*)\]""")
            val listMatch = listRegex.find(payload)
            if (listMatch != null) {
                return listMatch.groupValues[1]
            }

            val keyExistsRegex = Regex("""(?i)(?:^|[,{\s])["']?$escapedKey["']?\s*[:=]""")
            if (keyExistsRegex.containsMatchIn(payload)) {
                errors += "$key deve ser uma lista no formato [v1, v2]"
            }
            return null
        }

        val prefixRaw = extractRawList("prefix")
        val prefixes = if (prefixRaw != null) {
            prefixRaw
                .split(",")
                .map { it.trim().trim('"', '\'') }
                .filter { it.isNotBlank() }
                .distinct()
        } else {
            null
        }

        val epcLenRaw = extractRawList("epc_len")
        val epcLengths = if (epcLenRaw != null) {
            val parsed = mutableListOf<Int>()
            epcLenRaw
                .split(",")
                .map { it.trim().trim('"', '\'') }
                .filter { it.isNotBlank() }
                .forEach { token ->
                    val value = token.toIntOrNull()
                    if (value == null || value <= 0) {
                        errors += "EPC_LEN inválido: $token"
                    } else {
                        parsed += value
                    }
                }

            parsed.distinct()
        } else {
            null
        }

        return ParsedDeviceUidSettings(
            prefixes = prefixes,
            epcLengths = epcLengths,
            errors = errors
        )
    }

    private fun showSnackbar(msg: String) {
        val anchor = _binding?.root ?: activity?.window?.decorView ?: return
        Snackbar.make(anchor, msg, Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
