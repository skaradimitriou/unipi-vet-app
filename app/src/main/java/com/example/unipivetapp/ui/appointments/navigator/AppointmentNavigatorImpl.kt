package com.example.unipivetapp.ui.appointments.navigator

import android.app.Activity
import androidx.navigation.NavController
import com.example.unipivetapp.R
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class AppointmentNavigatorImpl @Inject constructor(
    private val activity: Activity?,
    private val navController: NavController
) : AppointmentNavigator {

    override fun navigateTo(screenKey: AppointmentAction) = when (screenKey) {
        AppointmentAction.BOOK -> navController.navigate(R.id.calendarFragment)
        AppointmentAction.OVERVIEW -> navController.navigate(R.id.overviewFragment)
        AppointmentAction.RESULT -> navController.navigate(R.id.resultFragment)
    }

    override fun goBack() {
        if (navController.graph.startDestinationId == navController.currentDestination?.id) {
            activity?.finish()
        } else {
            navController.navigateUp()
        }
    }
}