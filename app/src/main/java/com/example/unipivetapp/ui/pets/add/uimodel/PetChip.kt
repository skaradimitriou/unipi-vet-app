package com.example.unipivetapp.ui.pets.add.uimodel

import com.example.domain.models.pets.PetCategory
import com.example.domain.models.UiModel

data class PetChip(
    val category: PetCategory,
    var isSelected: Boolean = false
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is PetChip -> category.name == obj.category.name
        else -> false
    }
}
