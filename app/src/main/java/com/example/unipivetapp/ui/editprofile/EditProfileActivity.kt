package com.example.unipivetapp.ui.editprofile

import android.view.MenuItem
import androidx.activity.viewModels
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseActivity
import com.example.unipivetapp.databinding.ActivityEditProfileBinding
import com.example.unipivetapp.ui.editprofile.adapter.EditProfileAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileActivity :
    BaseActivity<ActivityEditProfileBinding>(R.layout.activity_edit_profile) {

    private val viewModel: EditProfileViewModel by viewModels()

    private val adapter = EditProfileAdapter { updatedData ->
        viewModel.saveUserData(updatedData)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            finish()
            false
        }
        else -> false
    }
}