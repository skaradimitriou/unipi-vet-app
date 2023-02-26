package com.example.unipivetapp.ui.dashboard.appointments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.models.AppointmentInfo
import com.example.domain.models.UiModel
import com.example.unipivetapp.base.BaseViewHolder
import com.example.unipivetapp.base.DiffUtilClass
import com.example.unipivetapp.databinding.HolderAppointmentItemBinding

class AppointmentsAdapter(
    private val callback: AppointmentCallback
) : ListAdapter<UiModel, AppointmentsViewHolder>(DiffUtilClass<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentsViewHolder {
        val view = HolderAppointmentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AppointmentsViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: AppointmentsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class AppointmentsViewHolder(
    private val binding: HolderAppointmentItemBinding,
    private val callback: AppointmentCallback
) : BaseViewHolder(binding) {

    override fun present(data: UiModel) = when (data) {
        is AppointmentInfo -> {
            binding.model = data
            binding.callback = callback
        }
        else -> Unit
    }
}

fun interface AppointmentCallback {
    fun onAppointmentClick(appointment: AppointmentInfo)
}