package com.example.unipivetapp.ui.pets.navigator

import android.app.Activity
import androidx.navigation.NavController
import com.example.unipivetapp.R
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PetNavigatorImpl @Inject constructor(
    private val activity: Activity?,
    private val navController: NavController
) : PetNavigator {

    override fun navigateTo(screenKey: PetAction) = when (screenKey) {
        PetAction.ADD_PET -> navController.navigate(R.id.addFragment)
        PetAction.ADD_PET_RESULT -> navController.navigate(R.id.petResultFragment)
        PetAction.DETAILS -> navController.navigate(R.id.detailsFragment)
        PetAction.EDIT_PET_DETAILS -> navController.navigate(R.id.editPetDetailsFragment)
    }

    override fun goBack() {
        if (navController.graph.startDestinationId == navController.currentDestination?.id) {
            activity?.finish()
        } else {
            navController.navigateUp()
        }
    }
}