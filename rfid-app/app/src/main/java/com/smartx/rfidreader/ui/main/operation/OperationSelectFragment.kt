package com.smartx.rfidreader.ui.main.operation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.smartx.rfidreader.core.settings.OperationType
import com.smartx.rfidreader.databinding.FragmentOperationSelectBinding
import com.smartx.rfidreader.ui.main.MainViewModel

class OperationSelectFragment : Fragment() {

    private var _binding: FragmentOperationSelectBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOperationSelectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnOperationEntry.setOnClickListener {
            viewModel.selectOperation(OperationType.ENTRY)
        }

        binding.btnOperationExit.setOnClickListener {
            viewModel.selectOperation(OperationType.EXIT)
        }

        binding.btnOperationDiscard.setOnClickListener {
            viewModel.selectOperation(OperationType.DISCARD)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
