package com.example.unipivetapp.ui.dashboard.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.models.FeaturedItem
import com.example.domain.models.UiModel
import com.example.unipivetapp.base.BaseViewHolder
import com.example.unipivetapp.base.DiffUtilClass
import com.example.unipivetapp.databinding.HolderFeaturedChildBinding

class HomeFeaturedAdapter(
    private val callback: HomeScreenCallback
) : ListAdapter<UiModel, HomeFeaturedHolder>(DiffUtilClass<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFeaturedHolder {
        val view = HolderFeaturedChildBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeFeaturedHolder(view, callback)
    }

    override fun onBindViewHolder(holder: HomeFeaturedHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class HomeFeaturedHolder(
    private val binding: HolderFeaturedChildBinding,
    private val callback: HomeScreenCallback
) : BaseViewHolder(binding) {

    override fun present(data: UiModel) = when (data) {
        is FeaturedItem -> {
            binding.model = data
            binding.callback = callback
        }
        else -> Unit
    }
}