package com.example.unipivetapp.ui.dashboard.profile.uimodel

data class ProfileScreenUiModel(
    val userImg: String,
    val username: String,
    val items: List<ProfileItem>
)

data class ProfileItem(
    val title: String,
    val value: String
)
