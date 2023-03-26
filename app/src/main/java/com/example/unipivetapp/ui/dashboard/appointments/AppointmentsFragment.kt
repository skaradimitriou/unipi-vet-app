package com.example.unipivetapp.ui.dashboard.appointments

import android.app.Activity
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentAppointmentsBinding
import com.example.unipivetapp.ui.appointmentdetails.AppointmentDetailsActivity
import com.example.unipivetapp.ui.dashboard.appointments.adapter.AppointmentsAdapter
import com.example.unipivetapp.util.APPOINTMENT
import com.example.unipivetapp.util.ext.setScreenTitle
import com.example.unipivetapp.util.ext.showSnackbar
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AppointmentsFragment :
    BaseFragment<FragmentAppointmentsBinding>(R.layout.fragment_appointments) {

    private val viewModel: AppointmentsViewModel by viewModels()

    @Inject
    lateinit var gson: Gson

    private val activityTask = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            binding.showSnackbar("Το ραντεβού ακυρώθηκε")
        }
    }

    private val adapter = AppointmentsAdapter { selectedAppointment ->
        val intent = Intent(requireContext(), AppointmentDetailsActivity::class.java).apply {
            val appointment = gson.toJson(selectedAppointment)
            putExtra(APPOINTMENT, appointment)
        }
        activityTask.launch(intent)
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

    override fun stopOps() {}
}