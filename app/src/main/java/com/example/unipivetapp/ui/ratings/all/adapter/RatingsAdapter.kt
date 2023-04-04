package com.example.unipivetapp.ui.ratings.all.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.models.Rating
import com.example.domain.models.UiModel
import com.example.unipivetapp.base.BaseViewHolder
import com.example.unipivetapp.base.DiffUtilClass
import com.example.unipivetapp.databinding.HolderRatingItemBinding

class RatingsAdapter : ListAdapter<UiModel, RatingsViewHolder>(DiffUtilClass<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingsViewHolder {
        val view = HolderRatingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RatingsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RatingsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class RatingsViewHolder(
    private val binding: HolderRatingItemBinding
) : BaseViewHolder(binding) {

    override fun present(data: UiModel) {
        when (data) {
            is Rating -> binding.model = data
        }
    }
}