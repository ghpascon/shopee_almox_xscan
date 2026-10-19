package com.smartx.rfidreader.ui.main

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.smartx.rfidreader.R
import com.smartx.rfidreader.core.reader.RfidTag
import com.smartx.rfidreader.core.reader.ReaderConnectionState
import com.smartx.rfidreader.core.settings.OperationType
import com.smartx.rfidreader.core.webhook.WebhookService
import com.smartx.rfidreader.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var registeredAdapter: HomeEpcAdapter
    private var currentTagCount: Int = 0
    private var isCurrentlyInventorying: Boolean = false
    private var allTagsWithDescription: Boolean = false
    private var hasRegisteredTags: Boolean = false
    private var currentUnregisteredEpcs: List<String> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupActions()
        observeState()
    }

    override fun onResume() {
        super.onResume()
        viewModel.clearTags()
        stopWebhookSendingOnHome()
    }

    private fun stopWebhookSendingOnHome() {
        if (!WebhookService.isRunning) return
        val stopIntent = Intent(requireContext(), WebhookService::class.java).apply {
            action = WebhookService.ACTION_STOP
        }
        requireContext().startService(stopIntent)
    }

    private fun setupRecyclerView() {
        registeredAdapter = HomeEpcAdapter { group ->
            showEpcListDialog(
                title = group.description,
                epcs = group.epcs
            )
        }

        binding.recyclerEpcs.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerEpcs.adapter = registeredAdapter
        binding.recyclerEpcs.isNestedScrollingEnabled = false
    }

    private fun setupActions() {
        binding.btnConfirm.isEnabled = false
        binding.btnConfirm.setOnClickListener {
            if (viewModel.uiState.value.appSettings.selectedOperation == OperationType.DISCARD) {
                viewModel.requestDiscardConditions()
            } else {
                viewModel.confirmMoveLocation()
            }
        }
        binding.btnChangeOperation.setOnClickListener {
            (requireActivity() as MainActivity).openOperationSelection()
        }
        binding.btnUnregisteredInfo.setOnClickListener {
            showEpcListDialog(
                title = getString(R.string.home_unregistered_title),
                epcs = currentUnregisteredEpcs
            )
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.tags.collect { tags ->
                        currentTagCount = tags.size
                        allTagsWithDescription = tags.isNotEmpty() && tags.all { it.description.isNotBlank() }

                        val unregisteredTags = tags.filter { it.descriptionLookupError }
                        val registeredTags = tags.filterNot { it.descriptionLookupError }
                        hasRegisteredTags = registeredTags.any { it.objectIdCode.isNotBlank() }
                        currentUnregisteredEpcs = unregisteredTags.map { it.epc }

                        updateReadingSummary(
                            registeredCount = registeredTags.size,
                            unregisteredCount = unregisteredTags.size
                        )
                        registeredAdapter.submitList(buildRegisteredGroups(registeredTags))
                        updateConfirmButtonState()
                    }
                }

                launch {
                    viewModel.uiState.collect { state ->
                        isCurrentlyInventorying = state.isInventorying
                        updateHomeStatus(state)
                        updateConfirmButtonLabel(state)
                        updateConfirmButtonState()
                    }
                }

                launch {
                    viewModel.moveLocationResult.collect { result ->
                        val activity = requireActivity() as MainActivity
                        activity.navigateTo(ConfirmResultFragment.newInstance(result))
                    }
                }

                launch {
                    viewModel.discardConditionOptions.collect { options ->
                        showDiscardReasonDialog(options)
                    }
                }

                launch {
                    viewModel.discardConditionError.collect { message ->
                        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun updateConfirmButtonState() {
        val state = viewModel.uiState.value
        val enabled = if (state.appSettings.selectedOperation == OperationType.DISCARD) {
            !isCurrentlyInventorying &&
                hasRegisteredTags &&
                !state.isLoadingDiscardConditions
        } else {
            currentTagCount > 0 &&
                !isCurrentlyInventorying &&
                allTagsWithDescription &&
                hasRegisteredTags
        }
        binding.btnConfirm.isEnabled = enabled
    }

    private fun updateConfirmButtonLabel(state: MainUiState) {
        val buttonText = when (state.appSettings.selectedOperation) {
            OperationType.ENTRY -> getString(R.string.home_btn_confirm_entry)
            OperationType.EXIT -> getString(R.string.home_btn_confirm_exit)
            OperationType.DISCARD -> {
                if (state.isLoadingDiscardConditions) getString(R.string.home_btn_loading_reasons)
                else getString(R.string.home_btn_select_reason)
            }
            OperationType.NONE -> getString(R.string.home_btn_confirm)
        }
        binding.btnConfirm.text = buttonText

        if (state.appSettings.selectedOperation == OperationType.DISCARD) {
            binding.btnConfirm.backgroundTintList = AppCompatResources.getColorStateList(
                requireContext(),
                R.color.btn_confirm_discard_bg
            )
            binding.btnConfirm.setTextColor(
                AppCompatResources.getColorStateList(
                    requireContext(),
                    R.color.btn_confirm_discard_text
                )
            )
        } else {
            binding.btnConfirm.backgroundTintList = AppCompatResources.getColorStateList(
                requireContext(),
                R.color.btn_confirm_default_bg
            )
            binding.btnConfirm.setTextColor(
                AppCompatResources.getColorStateList(
                    requireContext(),
                    R.color.btn_confirm_default_text
                )
            )
        }
    }

    private fun showDiscardReasonDialog(options: List<DiscardConditionOption>) {
        if (options.isEmpty()) {
            Snackbar.make(binding.root, getString(R.string.discard_reason_empty), Snackbar.LENGTH_LONG).show()
            return
        }

        val labels = options.map { it.name }.toTypedArray()
        var selectedIndex = -1

        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.discard_reason_title)
            .setSingleChoiceItems(labels, -1) { _, which ->
                selectedIndex = which
            }
            .setNegativeButton(R.string.btn_cancel, null)
            .setPositiveButton(R.string.btn_continue, null)
            .create()

        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                val selected = options.getOrNull(selectedIndex)
                if (selected == null) {
                    Snackbar.make(binding.root, getString(R.string.discard_reason_required), Snackbar.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                dialog.dismiss()
                showDiscardConfirmationDialog(selected)
            }
        }

        dialog.show()
    }

    private fun showDiscardConfirmationDialog(condition: DiscardConditionOption) {
        val piecesCount = viewModel.getRegisteredObjectCount()
        val message = getString(
            R.string.discard_confirm_message,
            piecesCount,
            condition.name
        )

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.discard_confirm_title)
            .setMessage(message)
            .setNegativeButton(R.string.btn_cancel, null)
            .setPositiveButton(R.string.btn_confirm_discard) { _, _ ->
                viewModel.confirmMoveCondition(condition.name)
            }
            .show()
    }

    private fun updateReadingSummary(registeredCount: Int, unregisteredCount: Int) {
        binding.textRegisteredCount.text = registeredCount.toString()
        binding.textUnregisteredCount.text = unregisteredCount.toString()
        binding.btnUnregisteredInfo.isEnabled = unregisteredCount > 0
        binding.btnUnregisteredInfo.alpha = if (unregisteredCount > 0) 1f else 0.4f
    }

    private fun buildRegisteredGroups(tags: List<RfidTag>): List<HomeEpcGroupItem> {
        return tags
            .groupBy { tag ->
                tag.description.trim().ifBlank { getString(R.string.tag_description_loading) }
            }
            .map { (description, groupedTags) ->
                HomeEpcGroupItem(
                    description = description,
                    count = groupedTags.size,
                    epcs = groupedTags.map { it.epc }.sorted()
                )
            }
            .sortedBy { it.description.lowercase() }
    }

    private fun showEpcListDialog(title: String, epcs: List<String>) {
        if (epcs.isEmpty()) {
            Snackbar.make(binding.root, getString(R.string.home_epc_list_empty), Snackbar.LENGTH_SHORT).show()
            return
        }

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.home_epc_list_title, title, epcs.size))
            .setItems(epcs.toTypedArray(), null)
            .setPositiveButton(R.string.btn_log_close, null)
            .show()
    }

    private fun updateHomeStatus(state: MainUiState) {
        val statusText = when {
            state.isInventorying -> "Lendo"
            state.connectionState == ReaderConnectionState.CONNECTING -> "Conectando..."
            state.connectionState == ReaderConnectionState.CONNECTED -> "Conectado"
            state.connectionState == ReaderConnectionState.ERROR -> "Erro"
            else -> "Desconectado"
        }

        val statusDrawable = when {
            state.isInventorying -> R.drawable.ic_status_active
            state.connectionState == ReaderConnectionState.CONNECTED -> R.drawable.ic_status_connected
            state.connectionState == ReaderConnectionState.CONNECTING -> R.drawable.ic_status_connected
            else -> R.drawable.ic_status_disconnected
        }

        binding.textStatusHome.text = statusText
        binding.statusDot.setBackgroundResource(statusDrawable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
