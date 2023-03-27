package com.example.unipivetapp.base

import androidx.databinding.ViewDataBinding
import com.example.domain.models.UiModel

class EmptyViewHolder(
    binding: ViewDataBinding
) : BaseViewHolder(binding) {
    override fun present(data: UiModel) {}
}