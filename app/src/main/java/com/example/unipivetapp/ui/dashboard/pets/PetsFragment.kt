package com.example.unipivetapp.ui.dashboard.pets

import androidx.fragment.app.viewModels
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentPetsBinding
import com.example.unipivetapp.ui.dashboard.pets.adapter.PetsAdapter
import com.example.unipivetapp.util.ext.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PetsFragment : BaseFragment<FragmentPetsBinding>(R.layout.fragment_pets) {

    private val viewModel: PetsViewModel by viewModels()

    private val adapter = PetsAdapter { selectedPet ->
        //FIXME: Open Pet details screen
    }

    override fun init() {
        setScreenTitle("Κατοικίδια")
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