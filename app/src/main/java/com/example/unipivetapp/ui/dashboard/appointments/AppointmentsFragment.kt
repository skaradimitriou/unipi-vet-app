package com.example.unipivetapp.ui.dashboard.appointments

import androidx.fragment.app.viewModels
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentAppointmentsBinding
import com.example.unipivetapp.ui.dashboard.appointments.adapter.AppointmentsAdapter
import com.example.unipivetapp.util.ext.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class AppointmentsFragment :
    BaseFragment<FragmentAppointmentsBinding>(R.layout.fragment_appointments) {

    private val viewModel: AppointmentsViewModel by viewModels()

    private val adapter = AppointmentsAdapter { selectedAppointment ->
        Timber.d("selectedAppointment => $selectedAppointment")
        //FIXME: Open Appointment Details Activity later on
    }

    override fun init() {
        setScreenTitle(getString(R.string.my_appointments_title))
        binding.adapter = adapter
    }

    override fun startOps() {
        viewModel.getMyAppointments()
        viewModel.appointments.observe(viewLifecycleOwner) { result ->
            when (result) {
                is com.example.domain.models.Result.Success -> {
                    adapter.submitList(result.data)
                }
                else -> Unit
            }
        }
    }

    override fun stopOps() {

    }
}