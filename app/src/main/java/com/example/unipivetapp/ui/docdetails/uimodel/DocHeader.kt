package com.example.unipivetapp.ui.docdetails.uimodel

import com.example.domain.models.UiModel

data class DocHeader(
    val image: String,
    val fullName: String,
    val category: String,
    val experience: Int,
    val rating: Double
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is DocHeader -> image == obj.image && fullName == obj.fullName
        else -> false
    }
}

data class DocReview(
    val title: String,
    val description: String
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is DocReview -> title == obj.title && description == obj.description
        else -> false
    }
}

data class DocAppointment(
    val title: String,
    val description: String,
    val btnText: String
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is DocAppointment -> btnText == obj.btnText
        else -> false
    }
}