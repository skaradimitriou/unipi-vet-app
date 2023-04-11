package com.example.unipivetapp.ui.pets.details

import androidx.fragment.app.activityViewModels
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentDetailsBinding
import com.example.unipivetapp.ui.pets.PetsViewModel
import com.example.unipivetapp.ui.pets.navigator.PetAction
import com.example.unipivetapp.util.ext.addAppBarMenu
import com.example.unipivetapp.util.ext.setScreenTitle
import com.example.unipivetapp.util.ext.withDelay
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(R.layout.fragment_details) {

    private val sharedViewModel: PetsViewModel by activityViewModels()

    override fun init() {
        setScreenTitle(getString(R.string.pet_details_title))

        addAppBarMenu(menuId = R.menu.profile_screen_menu) { selectedAction ->
            if (selectedAction == R.id.nav_edit_profile) {
                sharedViewModel.navigateToScreen(PetAction.EDIT_PET_DETAILS)
                withDelay(500L) {
                    sharedViewModel.resetNavigation()
                }
            }
        }
    }

    override fun startOps() {
        sharedViewModel.getSelectedPet()?.let {
            binding.model = it
        }
    }

    override fun stopOps() {}
}