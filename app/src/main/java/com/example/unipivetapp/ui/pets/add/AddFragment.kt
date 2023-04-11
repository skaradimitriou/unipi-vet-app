package com.example.unipivetapp.ui.pets.add

import android.content.Intent
import android.provider.MediaStore
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.domain.models.Result
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentAddBinding
import com.example.unipivetapp.ui.pets.PetsViewModel
import com.example.unipivetapp.ui.pets.add.adapter.ChipAdapter
import com.example.unipivetapp.ui.pets.navigator.PetAction
import com.example.unipivetapp.util.ext.onSuccessCameraResult
import com.example.unipivetapp.util.ext.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFragment : BaseFragment<FragmentAddBinding>(R.layout.fragment_add) {

    private val viewModel: AddViewModel by viewModels()
    private val sharedViewModel: PetsViewModel by activityViewModels()

    private val adapter = ChipAdapter { chip ->
        viewModel.setPetType(chip.category)
    }

    private val cameraIntent = onSuccessCameraResult { bitmap ->
        bitmap?.let {
            viewModel.setPetImage(it)
            binding.profileUserImgView.setImageBitmap(it)
        }
    }

    override fun init() {
        setScreenTitle(getString(R.string.add_new_pet_title))

        binding.adapter = adapter
        binding.chipsRecycler.itemAnimator = null

        binding.profileUserImgView.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraIntent.launch(intent)
        }

        binding.usernameEditTxt.doAfterTextChanged { text ->
            viewModel.validateInput(text.toString())
        }

        binding.continueBtn.setOnClickListener {
            viewModel.addPet()
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
                    sharedViewModel.navigateToScreen(PetAction.ADD_PET_RESULT)
                }
                is Result.Failure -> binding.isLoading = false
            }
        }
    }

    override fun stopOps() {}
}