package com.example.unipivetapp.ui.dashboard.pets

import android.content.Intent
import androidx.fragment.app.viewModels
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentPetsBinding
import com.example.unipivetapp.ui.dashboard.pets.adapter.PetsAdapter
import com.example.unipivetapp.ui.pets.PetsActivity
import com.example.unipivetapp.util.ADD_PET
import com.example.unipivetapp.util.PET
import com.example.unipivetapp.util.ext.addAppBarMenu
import com.example.unipivetapp.util.ext.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PetsFragment : BaseFragment<FragmentPetsBinding>(R.layout.fragment_pets) {

    private val viewModel: PetsViewModel by viewModels()

    private val adapter = PetsAdapter { selectedPet ->
        startActivity(Intent(requireContext(), PetsActivity::class.java).apply {
            putExtra(PET, selectedPet)
        })
    }

    override fun init() {
        setScreenTitle(getString(R.string.pets_title))
        addAppBarMenu(menuId = R.menu.pet_menu) { selectedAction ->
            if (selectedAction == R.id.nav_new_pet) {
                startActivity(Intent(requireContext(), PetsActivity::class.java).apply {
                    putExtra(ADD_PET, true)
                })
            }
        }
        binding.adapter = adapter
    }

    override fun startOps() {
        viewModel.getMyPets()
        viewModel.pets.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun stopOps() {}
}