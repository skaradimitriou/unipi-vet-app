package com.example.unipivetapp.ui.appointments.calendar.uimodel

import com.example.domain.models.UiModel
import java.time.LocalDate

data class Day(
    val value: LocalDate,
    var isSelected: Boolean = false
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is Day -> value == obj.value
        else -> false
    }
}
