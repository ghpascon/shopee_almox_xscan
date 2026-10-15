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
    private var lastLoadedLocationsUrl: String = ""
    private var lastTechnicalDetails: String = ""

    private data class LocationOption(
        val id: String,
        val name: String
    ) {
        override fun toString(): String {
            return if (name.isBlank()) id else "$name ($id)"
        }
    }

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
            showSnackbar(getString(R.string.app_settings_saved))
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
        syncSelectedLocationText(settings)
    }

    private fun saveUrlAndReturn(clearLocationIfUrlChanged: Boolean): String {
        val normalizedUrl = binding.editXtrackUrl.text?.toString()?.trim().orEmpty()
        val current = viewModel.uiState.value.appSettings
        val urlChanged = current.xtrackUrl.trim() != normalizedUrl

        val updated = if (clearLocationIfUrlChanged && urlChanged) {
            current.copy(
                xtrackUrl = normalizedUrl,
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
            binding.autoLocationEntry.setText("", false)
            binding.autoLocationExit.setText("", false)
            lastLoadedLocationsUrl = ""
        }

        return normalizedUrl
    }

    private fun maybeReloadLocations(xtrackUrl: String) {
        val normalized = xtrackUrl.trim()
        if (normalized.isBlank()) return
        if (normalized == lastLoadedLocationsUrl) return
        loadAvailableLocations(normalized)
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

    private fun setTechnicalDetails(message: String) {
        lastTechnicalDetails = message
        if (_binding != null) {
            binding.textXtrackDetails.text = lastTechnicalDetails
        }
    }

    private fun applyLocationOptions(locations: List<XTrackLocation>) {
        locationOptions = locations.map { LocationOption(id = it.id, name = it.name) }
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

    private fun showSnackbar(msg: String) {
        Snackbar.make(requireActivity().window.decorView, msg, Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
