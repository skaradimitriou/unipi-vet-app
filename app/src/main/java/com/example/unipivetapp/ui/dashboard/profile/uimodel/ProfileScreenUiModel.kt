package com.example.unipivetapp.ui.dashboard.profile.uimodel

import com.example.domain.models.UiModel

data class ProfileScreenUiModel(
    val userImg: String,
    val username: String,
    val items: List<ProfileOption>
)

data class ProfileOption(
    val title: String,
    val value: String,
    val type: ProfileOptionType = ProfileOptionType.OPTION
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is ProfileOption -> title == obj.title && value == obj.value
        else -> false
    }
}

enum class ProfileOptionType {
    OPTION, LOGOUT
}