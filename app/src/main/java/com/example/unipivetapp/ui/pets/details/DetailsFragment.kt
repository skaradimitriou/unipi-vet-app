package com.example.unipivetapp.ui.pets.details

import androidx.fragment.app.viewModels
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentDetailsBinding
import com.example.unipivetapp.ui.pets.PetsViewModel
import com.example.unipivetapp.util.ext.addAppBarMenu
import com.example.unipivetapp.util.ext.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment<FragmentDetailsBinding>(R.layout.fragment_details) {

    private val viewModel: DetailsViewModel by viewModels()
    private val sharedViewModel: PetsViewModel by viewModels()

    override fun init() {
        setScreenTitle(getString(R.string.pet_details_title))

        addAppBarMenu(menuId = R.menu.profile_screen_menu) { selectedAction ->
            if (selectedAction == R.id.nav_edit_profile) {
                //FIXME: Go to edit pet details
            }
        }
    }

    override fun startOps() {

    }

    override fun stopOps() {

    }
}