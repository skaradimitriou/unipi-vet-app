package com.example.unipivetapp.ui.dashboard.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.models.UiModel
import com.example.unipivetapp.base.BaseViewHolder
import com.example.unipivetapp.base.DiffUtilClass
import com.example.unipivetapp.databinding.HolderProfileOptionItemBinding
import com.example.unipivetapp.ui.dashboard.profile.uimodel.ProfileOption

class ProfileOptionsAdapter(
    private val callback: ProfileOptionCallback
) : ListAdapter<UiModel, ProfileOptionsViewHolder>(DiffUtilClass<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileOptionsViewHolder {
        val view = HolderProfileOptionItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProfileOptionsViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: ProfileOptionsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ProfileOptionsViewHolder(
    private val binding: HolderProfileOptionItemBinding,
    private val callback: ProfileOptionCallback
) : BaseViewHolder(binding) {

    override fun present(data: UiModel) = when (data) {
        is ProfileOption -> {
            binding.model = data
            binding.callback = callback
        }
        else -> Unit
    }
}

fun interface ProfileOptionCallback {
    fun onOptionClick(option: ProfileOption)
}