package com.example.unipivetapp.ui.editprofile

import android.content.Intent
import android.provider.MediaStore
import android.view.MenuItem
import androidx.activity.viewModels
import com.example.domain.models.UpdateUserInfo
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseActivity
import com.example.unipivetapp.databinding.ActivityEditProfileBinding
import com.example.unipivetapp.ui.editprofile.adapter.EditProfileAdapter
import com.example.unipivetapp.ui.editprofile.adapter.EditProfileScreenCallback
import com.example.unipivetapp.util.ext.onSuccessCameraResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileActivity :
    BaseActivity<ActivityEditProfileBinding>(R.layout.activity_edit_profile) {

    private val viewModel: EditProfileViewModel by viewModels()

    private val adapter = EditProfileAdapter(object : EditProfileScreenCallback {
        override fun onImageClick() = capturePhoto()
        override fun onSaveButtonClick(userInfo: UpdateUserInfo) = viewModel.saveUserData(userInfo)
    })

    private val cameraIntent = onSuccessCameraResult { bitmap ->
        bitmap?.let {
            viewModel.saveUserPhoto(it)
        }
    }

    override fun init() {
        title = getString(R.string.edit_profile_title)
        binding.adapter = adapter
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun startOps() {
        viewModel.getProfileInfo()

        viewModel.userInfo.observe(this) { uiData ->
            adapter.submitList(uiData)
        }

        viewModel.profileUpdated.observe(this) { updated ->
            if (updated) finish()
        }
    }

    override fun stopOps() {}

    private fun capturePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.launch(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            finish()
            false
        }
        else -> false
    }
}