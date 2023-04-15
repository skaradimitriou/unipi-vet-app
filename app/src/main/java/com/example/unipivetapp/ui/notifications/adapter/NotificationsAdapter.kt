package com.example.unipivetapp.ui.notifications.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.models.Notification
import com.example.domain.models.UiModel
import com.example.unipivetapp.base.BaseViewHolder
import com.example.unipivetapp.base.DiffUtilClass
import com.example.unipivetapp.databinding.HolderNotificationItemBinding

class NotificationsAdapter :
    ListAdapter<UiModel, NotificationsViewHolder>(DiffUtilClass<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationsViewHolder {
        val view = HolderNotificationItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NotificationsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class NotificationsViewHolder(
    private val binding: HolderNotificationItemBinding
) : BaseViewHolder(binding) {

    override fun present(data: UiModel) = when (data) {
        is Notification -> binding.model = data
        else -> Unit
    }
}