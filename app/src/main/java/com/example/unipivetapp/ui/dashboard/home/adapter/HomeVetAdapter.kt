package com.example.unipivetapp.ui.dashboard.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.models.UiModel
import com.example.domain.models.Vet
import com.example.unipivetapp.base.BaseViewHolder
import com.example.unipivetapp.base.DiffUtilClass
import com.example.unipivetapp.databinding.HolderHomeVetItemBinding
import com.example.unipivetapp.ui.dashboard.vets.VetCallback

class HomeVetAdapter(
    private val callback: VetCallback
) : ListAdapter<UiModel, HomeVetViewHolder>(DiffUtilClass<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeVetViewHolder {
        val view = HolderHomeVetItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeVetViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: HomeVetViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class HomeVetViewHolder(
    private val binding: HolderHomeVetItemBinding,
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