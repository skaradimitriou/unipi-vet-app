package com.example.unipivetapp.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.UiModel

abstract class BaseViewHolder(binding : ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data : UiModel){
        present(data)
    }

    abstract fun present(data : UiModel)
}