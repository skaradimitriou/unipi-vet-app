package com.example.unipivetapp.ui.pets.edit

import android.content.Intent
import android.provider.MediaStore
import androidx.core.graphics.drawable.toBitmap
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.domain.models.Result
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentEditPetDetailsBinding
import com.example.unipivetapp.ui.pets.PetsViewModel
import com.example.unipivetapp.ui.pets.add.adapter.ChipAdapter
import com.example.unipivetapp.ui.pets.navigator.PetAction
import com.example.unipivetapp.util.ext.onSuccessCameraResult
import com.example.unipivetapp.util.ext.setScreenTitle
import com.example.unipivetapp.util.setImageFromUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditPetDetailsFragment :
    BaseFragment<FragmentEditPetDetailsBinding>(R.layout.fragment_edit_pet_details) {

    private val viewModel: EditPetDetailsViewModel by viewModels()
    private val sharedViewModel: PetsViewModel by activityViewModels()

    private val adapter = ChipAdapter { chip ->
        viewModel.setPetType(chip.category)
    }

    private val cameraIntent = onSuccessCameraResult { bitmap ->
        bitmap?.let {
            binding.petImgView.setImageBitmap(it)
        }
    }

    override fun init() {
        setScreenTitle(getString(R.string.edit_pet_info))

        binding.adapter = adapter
        binding.chipsRecycler.itemAnimator = null

        sharedViewModel.getSelectedPet()?.let { pet ->
            viewModel.originalPet = pet

            viewModel.getPetImage()?.let {
                binding.petImgView.setImageBitmap(it)
            } ?: kotlin.run {
                binding.petImgView.setImageFromUrl(pet.image)
            }

            binding.usernameEditTxt.setText(pet.nickname)
            viewModel.setPetType(pet.type)
        }

        binding.petImgView.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraIntent.launch(intent)
        }

        binding.usernameEditTxt.doAfterTextChanged { text ->
            viewModel.validateInput(text.toString())
        }

        binding.continueBtn.setOnClickListener {
            val image = binding.petImgView.drawable.toBitmap()
            viewModel.setPetImage(image)
            viewModel.updatePet()
        }
    }

    override fun startOps() {
        viewModel.getChips()

        viewModel.btnState.observe(viewLifecycleOwner) { isEnabled ->
            binding.continueBtn.isEnabled = isEnabled
        }

        viewModel.chips.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.petAdded.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> binding.isLoading = true
                is Result.Success -> {
                    binding.isLoading = false
                    sharedViewModel.navigateToScreen(PetAction.EDIT_PET_RESULT)
                }
                is Result.Failure -> binding.isLoading = false
            }
        }
    }

    override fun stopOps() {}
}