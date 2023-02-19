package com.example.unipivetapp.base

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.models.UiModel

class DiffUtilClass<T : UiModel> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.equalsContent(newItem)
    }
}