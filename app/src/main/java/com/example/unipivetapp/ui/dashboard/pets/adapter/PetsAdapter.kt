package com.example.unipivetapp.ui.dashboard.pets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.models.pets.Pet
import com.example.domain.models.UiModel
import com.example.unipivetapp.base.BaseViewHolder
import com.example.unipivetapp.base.DiffUtilClass
import com.example.unipivetapp.databinding.HolderPetItemBinding

class PetsAdapter(
    private val callback: PetCallback
) : ListAdapter<UiModel, PetsViewHolder>(DiffUtilClass<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetsViewHolder {
        val view = HolderPetItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PetsViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: PetsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PetsViewHolder(
    private val binding: HolderPetItemBinding,
    private val callback: PetCallback
) : BaseViewHolder(binding) {

    override fun present(data: UiModel) {
        when (data) {
            is Pet -> {
                binding.model = data
                binding.callback = callback
            }
        }
    }
}

fun interface PetCallback {
    fun onPetClick(pet: Pet)
}