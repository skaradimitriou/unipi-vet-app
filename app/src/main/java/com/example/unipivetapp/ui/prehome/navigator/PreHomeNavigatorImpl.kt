package com.example.unipivetapp.ui.prehome.navigator

import android.app.Activity
import androidx.navigation.NavController
import com.example.unipivetapp.R
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PreHomeNavigatorImpl @Inject constructor(
    private val activity: Activity?,
    private val navController: NavController
) : PreHomeNavigator {

    override fun navigateTo(screenKey: PreHomeAction) = when (screenKey) {
        PreHomeAction.PERSONALIZE -> navController.navigate(R.id.personalizeFragment)
        PreHomeAction.ADDITIONAL -> navController.navigate(R.id.additionalFragment)
    }

    override fun goBack() {
        if (navController.graph.startDestinationId == navController.currentDestination?.id) {
            activity?.finish()
        } else {
            navController.navigateUp()
        }
    }
}