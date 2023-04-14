package com.example.unipivetapp.ui.appointments.calendar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.models.UiModel
import com.example.unipivetapp.base.BaseViewHolder
import com.example.unipivetapp.base.DiffUtilClass
import com.example.unipivetapp.databinding.HolderDayItemBinding
import com.example.unipivetapp.ui.appointments.calendar.uimodel.Day
import java.time.LocalDate

class DaysAdapter(
    private val callback: DayCallback
) : ListAdapter<UiModel, DaysAdapter.DaysViewHolder>(DiffUtilClass<UiModel>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysViewHolder {
        val view = HolderDayItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DaysViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: DaysViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DaysViewHolder(
        private val binding: HolderDayItemBinding,
        private val callback: DayCallback
    ) : BaseViewHolder(binding) {

        override fun present(data: UiModel) = when (data) {
            is Day -> {
                binding.model = data
                binding.itemConstraint.setOnClickListener {
                    val item = (currentList.find { (it as Day).isSelected }) as Day

                    if (data.value < LocalDate.now() || item.value < LocalDate.now()) {
                        callback.onEarlierDayThanToday()
                    } else {
                        item.isSelected = !item.isSelected
                        data.isSelected = !data.isSelected
                        notifyItemChanged(currentList.indexOf(item))
                        notifyItemChanged(currentList.indexOf(data))
                        callback.onDayClick(data)
                    }
                }
            }
            else -> Unit
        }
    }
}

interface DayCallback {
    fun onDayClick(model: Day)
    fun onEarlierDayThanToday()
}