package com.example.unipivetapp.ui.dashboard.vets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.models.UiModel
import com.example.domain.models.Vet
import com.example.unipivetapp.base.BaseViewHolder
import com.example.unipivetapp.base.DiffUtilClass
import com.example.unipivetapp.databinding.HolderVetItemBinding

class VetAdapter(
    private val callback: VetCallback
) : ListAdapter<UiModel, VetViewHolder>(DiffUtilClass<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VetViewHolder {
        val view = HolderVetItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VetViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: VetViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class VetViewHolder(
    private val binding: HolderVetItemBinding,
    private val callback: VetCallback
) : BaseViewHolder(binding) {

    override fun present(data: UiModel) = when (data) {
        is Vet -> {
            binding.model = data
            binding.callback = callback
        }
        else -> Unit
    }
}

fun interface VetCallback {
    fun onVetClick(model: Vet)
}