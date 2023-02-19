package com.example.unipivetapp.ui.dashboard.appointments

import androidx.fragment.app.viewModels
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentAppointmentsBinding
import com.example.unipivetapp.util.ext.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppointmentsFragment :
    BaseFragment<FragmentAppointmentsBinding>(R.layout.fragment_appointments) {

    private val viewModel: AppointmentsViewModel by viewModels()

    override fun init() {
        setScreenTitle("Appointments")
    }

    override fun startOps() {

    }

    override fun stopOps() {

    }
}