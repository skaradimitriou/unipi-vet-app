package com.example.unipivetapp.ui.editprofile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.UiModel
import com.example.domain.models.UpdateUserInfo
import com.example.domain.models.UserInfo
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseViewHolder
import com.example.unipivetapp.base.DiffUtilClass
import com.example.unipivetapp.base.EmptyViewHolder
import com.example.unipivetapp.databinding.HolderEditProfileSaveBinding
import com.example.unipivetapp.databinding.HolderEmptyItemBinding
import com.example.unipivetapp.databinding.HolderProfileHeaderBinding
import com.example.unipivetapp.ui.dashboard.profile.uimodel.ProfileHeader

class EditProfileAdapter(
    private val callback: EditProfileScreenCallback
) : ListAdapter<UiModel, RecyclerView.ViewHolder>(DiffUtilClass<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.holder_profile_header -> {
                val view = HolderProfileHeaderBinding.inflate(inflater, parent, false)
                EditProfileHeaderViewHolder(view, callback)
            }
            R.layout.holder_edit_profile_save -> {
                val view = HolderEditProfileSaveBinding.inflate(inflater, parent, false)
                EditProfileViewHolder(view, callback)
            }
            else -> {
                val view = HolderEmptyItemBinding.inflate(inflater, parent, false)
                EmptyViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = when (holder) {
        is EditProfileHeaderViewHolder -> holder.bind(getItem(position))
        is EditProfileViewHolder -> holder.bind(getItem(position))
        else -> Unit
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is ProfileHeader -> R.layout.holder_profile_header
        is UserInfo -> R.layout.holder_edit_profile_save
        else -> R.layout.holder_empty_item
    }
}

class EditProfileHeaderViewHolder(
    private val binding: HolderProfileHeaderBinding,
    private val callback: EditProfileScreenCallback
) : BaseViewHolder(binding) {

    override fun present(data: UiModel) {
        when (data) {
            is ProfileHeader -> {
                binding.model = data
                binding.profileUserImgView.setOnClickListener {
                    callback.onImageClick()
                }
            }
        }
    }
}

class EditProfileViewHolder(
    private val binding: HolderEditProfileSaveBinding,
    private val callback: EditProfileScreenCallback
) : BaseViewHolder(binding) {

    override fun present(data: UiModel) {
        when (data) {
            is UserInfo -> {
                binding.model = data
                binding.primaryBtn.setOnClickListener {
                    val updatedData = UpdateUserInfo(
                        firstName = binding.fNameEditTxt.text.toString(),
                        lastName = binding.lNameEditTxt.text.toString(),
                        telephone = binding.telEditTxt.text.toString(),
                        username = binding.usernameEditTxt.text.toString()
                    )
                    callback.onSaveButtonClick(updatedData)
                }
            }
        }
    }
}

interface EditProfileScreenCallback {
    fun onImageClick()
    fun onSaveButtonClick(userInfo: UpdateUserInfo)
}