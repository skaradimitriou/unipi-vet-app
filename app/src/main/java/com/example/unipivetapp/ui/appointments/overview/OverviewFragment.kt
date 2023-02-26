package com.example.unipivetapp.ui.appointments.overview

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.domain.models.Result
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentOverviewBinding
import com.example.unipivetapp.ui.appointments.AppointmentsSharedViewModel
import com.example.unipivetapp.ui.appointments.AppointmentsViewModel
import com.example.unipivetapp.ui.appointments.navigator.AppointmentAction
import com.example.unipivetapp.util.ext.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OverviewFragment : BaseFragment<FragmentOverviewBinding>(R.layout.fragment_overview) {

    private val viewModel: OverviewViewModel by viewModels()
    private val sharedViewModel: AppointmentsSharedViewModel by activityViewModels()
    private val activityViewModel: AppointmentsViewModel by activityViewModels()

    override fun init() {
        setScreenTitle(getString(R.string.overview_title))
        binding.sharedViewModel = sharedViewModel

        binding.continueBtn.setOnClickListener {
            val appointment = sharedViewModel.appointment.value
            val vet = sharedViewModel.selectedVet.value

            if (appointment != null && vet != null) {
                viewModel.saveAppointment(appointment, vet)
            }
        }
    }

    override fun startOps() {
        sharedViewModel.getAppointmentData()

        viewModel.appointmentSaved.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> binding.continueBtn.isEnabled = false
                is Result.Success -> activityViewModel.navigateToScreen(AppointmentAction.RESULT)
                is Result.Failure -> binding.continueBtn.isEnabled = true
            }
        }
    }

    override fun stopOps() {}
}