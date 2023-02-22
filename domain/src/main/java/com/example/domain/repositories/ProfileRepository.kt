package com.example.domain.repositories

import com.example.domain.models.UserInfo

interface ProfileRepository {

    suspend fun setProfileInfo(email: String, uuid: String)

    suspend fun getProfileInfo(uuid: String): UserInfo
}