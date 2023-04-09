package com.example.unipivetapp.ui.pets.edit

import androidx.fragment.app.viewModels
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentEditPetDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditPetDetailsFragment :
    BaseFragment<FragmentEditPetDetailsBinding>(R.layout.fragment_edit_pet_details) {

    private val viewModel: EditPetDetailsViewModel by viewModels()

    override fun init() {

    }

    override fun startOps() {

    }

    override fun stopOps() {

    }
}