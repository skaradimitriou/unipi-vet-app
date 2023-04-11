package com.example.unipivetapp.ui.pets.result

import androidx.fragment.app.activityViewModels
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentPetResultBinding
import com.example.unipivetapp.ui.pets.PetsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PetResultFragment : BaseFragment<FragmentPetResultBinding>(R.layout.fragment_pet_result) {

    private val sharedViewModel: PetsViewModel by activityViewModels()

    override fun init() {
        binding.continueBtn.setOnClickListener {
            requireActivity().finish()
        }
    }

    override fun startOps() {
        sharedViewModel.navigatorState.observe(viewLifecycleOwner) { action ->
            action?.let {
                binding.action = action
                sharedViewModel.resetNavigation()
            }
        }
    }

    override fun stopOps() {}
}