package com.example.unipivetapp.ui.onboarding.navigator

import android.app.Activity
import androidx.navigation.NavController
import com.example.unipivetapp.R
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class OnboardingNavigatorImpl @Inject constructor(
    private val activity: Activity?,
    private val navController: NavController
) : Navigator {

    override fun navigateTo(screenKey: OnboardingAction) = when (screenKey) {
        OnboardingAction.INTRO -> navController.navigate(R.id.introFragment)
        OnboardingAction.LOGIN -> navController.navigate(R.id.loginFragment)
        OnboardingAction.REGISTER -> navController.navigate(R.id.registerFragment)
    }

    override fun goBack() {
        if (navController.graph.startDestinationId == navController.currentDestination?.id) {
            activity?.finish()
        } else {
            navController.navigateUp()
        }
    }
}