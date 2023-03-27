package com.example.domain.repositories

import com.example.domain.models.UpdateUserInfo
import com.example.domain.models.UserInfo

interface ProfileRepository {

    suspend fun updateProfileInfo(email: String, uuid: String)

    suspend fun updateProfileInfo(data: UpdateUserInfo, uuid: String)

    suspend fun getProfileInfo(uuid: String): UserInfo
}