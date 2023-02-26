package com.example.unipivetapp.ui.appointments.overview

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
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
    }

    override fun startOps() {
        sharedViewModel.getAppointmentData()

        binding.continueBtn.setOnClickListener {
            activityViewModel.navigateToScreen(AppointmentAction.RESULT)
        }
    }

    override fun stopOps() {}
}