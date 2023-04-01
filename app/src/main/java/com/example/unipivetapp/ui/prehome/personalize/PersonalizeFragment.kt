package com.example.unipivetapp.ui.prehome.personalize

import android.content.Intent
import android.provider.MediaStore
import androidx.fragment.app.activityViewModels
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseFragment
import com.example.unipivetapp.databinding.FragmentPersonalizeBinding
import com.example.unipivetapp.ui.prehome.PreHomeSharedViewModel
import com.example.unipivetapp.ui.prehome.PreHomeViewModel
import com.example.unipivetapp.ui.prehome.navigator.PreHomeAction
import com.example.unipivetapp.util.ext.onSuccessCameraResult
import com.example.unipivetapp.util.ext.setScreenTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonalizeFragment :
    BaseFragment<FragmentPersonalizeBinding>(R.layout.fragment_personalize) {

    private val activityViewModel: PreHomeViewModel by activityViewModels()
    private val sharedViewModel: PreHomeSharedViewModel by activityViewModels()

    private val cameraIntent = onSuccessCameraResult { bitmap ->
        bitmap?.let {
            sharedViewModel.setImage(it)
            binding.profileUserImgView.setImageBitmap(it)
        }
    }

    override fun init() {
        setScreenTitle(getString(R.string.pre_home_additionals_title))
    }

    override fun startOps() {
        binding.profileUserImgView.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraIntent.launch(intent)
        }

        binding.continueBtn.setOnClickListener {
            sharedViewModel.setUsername(binding.usernameEditTxt.text.toString())
            activityViewModel.navigateToScreen(PreHomeAction.ADDITIONAL)
        }
    }

    override fun stopOps() {}
}