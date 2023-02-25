package com.example.unipivetapp.ui.appointments.calendar.uimodel

import com.example.domain.models.UiModel

data class TimeSlot(
    val value: String,
    var isSelected: Boolean = false
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is TimeSlot -> value == obj.value && isSelected == obj.isSelected
        else -> false
    }
}
