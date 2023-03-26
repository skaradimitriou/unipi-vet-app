package com.example.unipivetapp.ui.dashboard.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.models.UiModel
import com.example.unipivetapp.BR
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseViewHolder
import com.example.unipivetapp.base.DiffUtilClass
import com.example.unipivetapp.databinding.HolderEmptyItemBinding
import com.example.unipivetapp.databinding.HolderLogoutOptionBinding
import com.example.unipivetapp.databinding.HolderProfileHeaderBinding
import com.example.unipivetapp.databinding.HolderProfileOptionItemBinding
import com.example.unipivetapp.ui.dashboard.profile.uimodel.ProfileHeader
import com.example.unipivetapp.ui.dashboard.profile.uimodel.ProfileLogoutOption
import com.example.unipivetapp.ui.dashboard.profile.uimodel.ProfileOption

class ProfileOptionsAdapter(
    private val callback: ProfileScreenCallback
) : ListAdapter<UiModel, ProfileOptionsViewHolder>(DiffUtilClass<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileOptionsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = when (viewType) {
            R.layout.holder_profile_header -> {
                HolderProfileHeaderBinding.inflate(inflater, parent, false)
            }
            R.layout.holder_profile_option_item -> {
                HolderProfileOptionItemBinding.inflate(inflater, parent, false)
            }
            R.layout.holder_logout_option -> {
                HolderLogoutOptionBinding.inflate(inflater, parent, false)
            }
            else -> HolderEmptyItemBinding.inflate(inflater, parent, false)
        }
        return ProfileOptionsViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: ProfileOptionsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is ProfileHeader -> R.layout.holder_profile_header
        is ProfileOption -> R.layout.holder_profile_option_item
        is ProfileLogoutOption -> R.layout.holder_logout_option
        else -> R.layout.holder_empty_item
    }
}

class ProfileOptionsViewHolder(
    private val binding: ViewDataBinding,
    private val callback: ProfileScreenCallback
) : BaseViewHolder(binding) {

    override fun present(data: UiModel) {
        when (data) {
            is ProfileHeader -> binding.setVariable(BR.model, data)
            is ProfileOption -> binding.setVariable(BR.model, data)
            is ProfileLogoutOption -> {
                binding.setVariable(BR.model, data)
                binding.setVariable(BR.callback, callback)
            }
        }
    }
}

fun interface ProfileScreenCallback {
    fun onLogoutClick()
}