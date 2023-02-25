package com.example.unipivetapp.ui.appointments

import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.domain.models.Vet
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseActivity
import com.example.unipivetapp.databinding.ActivityAppointmentsBinding
import com.example.unipivetapp.ui.appointments.navigator.AppointmentNavigatorImpl
import com.example.unipivetapp.util.VET
import com.example.unipivetapp.util.ext.getParcelable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppointmentsActivity :
    BaseActivity<ActivityAppointmentsBinding>(R.layout.activity_appointments) {

    private val viewModel: AppointmentsViewModel by viewModels()
    private val sharedViewModel: AppointmentsSharedViewModel by viewModels()

    private lateinit var navController: NavController
    private lateinit var navigator: AppointmentNavigatorImpl

    override fun init() {
        navController = findNavController(R.id.nav_host_fragment)
        navigator = AppointmentNavigatorImpl(this, navController)

        intent.getParcelable<Vet>(VET)?.let {
            sharedViewModel.setSelectedVet(it)
        }

        navController.addOnDestinationChangedListener { _, _, _ ->
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }

    override fun startOps() {
        viewModel.navigatorState.observe(this) { action ->
            action?.let { navigator.navigateTo(it) }
        }
    }

    override fun stopOps() {
        viewModel.navigatorState.removeObservers(this)
    }

    override fun onBackPressed() {
        navigator.goBack()
        viewModel.resetNavigation()
    }
}