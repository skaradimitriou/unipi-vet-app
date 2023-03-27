package com.example.unipivetapp.ui.dashboard.profile.uimodel

import com.example.domain.models.UiModel

data class ProfileHeader(
    val userImg: String,
    val username: String,
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is ProfileHeader -> userImg == obj.userImg && username == obj.username
        else -> false
    }
}

data class ProfileOption(
    val title: String,
    val value: String
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is ProfileOption -> title == obj.title && value == obj.value
        else -> false
    }
}

data class ProfileLogoutOption(
    val buttonTxt: String
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is ProfileLogoutOption -> buttonTxt == obj.buttonTxt
        else -> false
    }
}