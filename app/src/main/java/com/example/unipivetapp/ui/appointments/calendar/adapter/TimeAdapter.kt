package com.example.unipivetapp.ui.appointments.calendar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.models.UiModel
import com.example.unipivetapp.base.BaseViewHolder
import com.example.unipivetapp.base.DiffUtilClass
import com.example.unipivetapp.databinding.HolderTimeSlotBinding
import com.example.unipivetapp.ui.appointments.calendar.uimodel.TimeSlot

class TimeAdapter(
    private val callback: TimeCallback
) : ListAdapter<UiModel, TimeAdapter.TimeViewHolder>(DiffUtilClass<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder {
        val view = HolderTimeSlotBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TimeViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TimeViewHolder(
        private val binding: HolderTimeSlotBinding,
        private val callback: TimeCallback
    ) : BaseViewHolder(binding) {

        override fun present(data: UiModel) = when (data) {
            is TimeSlot -> {
                binding.model = data
                binding.itemConstraint.setOnClickListener {
                    val item = (currentList.find { (it as TimeSlot).isSelected }) as TimeSlot
                    item.isSelected = !item.isSelected
                    data.isSelected = !data.isSelected
                    notifyItemChanged(currentList.indexOf(item))
                    notifyItemChanged(currentList.indexOf(data))
                    callback.onTimeClick(data)
                }
            }
            else -> Unit
        }
    }
}


fun interface TimeCallback {
    fun onTimeClick(model: TimeSlot)
}