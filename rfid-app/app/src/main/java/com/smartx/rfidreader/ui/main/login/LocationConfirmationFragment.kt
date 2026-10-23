package com.smartx.rfidreader.ui.main.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smartx.rfidreader.databinding.FragmentLocationConfirmationBinding
import com.smartx.rfidreader.ui.main.MainActivity

class LocationConfirmationFragment : Fragment() {

    private var _binding: FragmentLocationConfirmationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationConfirmationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textEntryLocationConfirmation.text =
            requireArguments().getString(ARG_LOCATION_NAME).orEmpty()

        binding.btnConfirmEntryLocation.setOnClickListener {
            (requireActivity() as MainActivity).continueToLoginAfterLocationConfirmed()
        }
        binding.btnRejectEntryLocation.setOnClickListener {
            showHelpScreen()
        }
        binding.btnBackToLocationConfirmation.setOnClickListener {
            binding.groupLocationHelp.visibility = View.GONE
            binding.groupLocationConfirmation.visibility = View.VISIBLE
        }
    }

    private fun showHelpScreen() {
        binding.groupLocationConfirmation.visibility = View.GONE
        binding.groupLocationHelp.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_LOCATION_NAME = "location_name"

        fun newInstance(locationName: String) = LocationConfirmationFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_LOCATION_NAME, locationName)
            }
        }
    }
}
