package com.smartx.rfidreader.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.smartx.rfidreader.R

class ManualBarcodeScanDialogFragment : DialogFragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var textLastValue: TextView
    private lateinit var editCapture: EditText
    private var internalEditUpdate = false
    private var closingAfterSuccess = false

    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
        val contentView = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_manual_barcode_scan, null)
        textLastValue = contentView.findViewById(R.id.textManualScanLastValue)
        editCapture = contentView.findViewById(R.id.editManualScanCapture)

        editCapture.setOnEditorActionListener { _, _, _ ->
            consumeBarcode(editCapture.text?.toString().orEmpty())
            true
        }
        editCapture.setOnKeyListener { _, keyCode, event ->
            if (event.action == android.view.KeyEvent.ACTION_DOWN &&
                (keyCode == android.view.KeyEvent.KEYCODE_ENTER || keyCode == android.view.KeyEvent.KEYCODE_NUMPAD_ENTER)
            ) {
                consumeBarcode(editCapture.text?.toString().orEmpty())
                true
            } else {
                false
            }
        }
        editCapture.doAfterTextChanged { editable ->
            if (internalEditUpdate) return@doAfterTextChanged
            val value = editable?.toString().orEmpty()
            if (value.contains('\n') || value.contains('\r')) {
                consumeBarcode(value)
            }
        }

        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setView(contentView)
            .setNegativeButton(R.string.btn_cancel, null)
            .create()

        dialog.setOnShowListener {
            editCapture.requestFocus()
        }

        return dialog
    }

    private fun consumeBarcode(rawValue: String) {
        if (closingAfterSuccess) return

        val value = rawValue.replace("\r", "").replace("\n", "").trim()
        internalEditUpdate = true
        editCapture.text?.clear()
        internalEditUpdate = false

        val error = mainViewModel.addManualBarcodeAsEpc(value)
        if (error != null) {
            textLastValue.text = error
            editCapture.requestFocus()
            return
        }

        closingAfterSuccess = true
        dismissAllowingStateLoss()
    }
}
