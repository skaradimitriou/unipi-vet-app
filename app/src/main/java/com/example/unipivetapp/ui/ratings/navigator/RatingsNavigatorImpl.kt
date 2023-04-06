package com.example.unipivetapp.ui.ratings.navigator

import android.app.Activity
import androidx.navigation.NavController
import com.example.unipivetapp.R
import dagger.hilt.android.scopes.ActivityScoped

@ActivityScoped
class RatingsNavigatorImpl(
    private val activity: Activity?,
    private val navController: NavController
) : RatingsNavigator {

    override fun navigateTo(screenKey: RatingsAction) = when (screenKey) {
        RatingsAction.ADD_NEW_RATING -> navController.navigate(R.id.addRatingFragment)
        RatingsAction.NEW_RATING_ADDED -> goBackToAllRatings()
        RatingsAction.VIEW_ALL -> navController.navigate(R.id.allRatingsFragment)
    }

    override fun goBack() {
        activity?.finish()
    }

    private fun goBackToAllRatings() {
        navController.popBackStack()
    }
}