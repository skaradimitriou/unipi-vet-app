package com.example.domain.repositories

import android.graphics.Bitmap
import com.example.domain.models.UpdateUserInfo
import com.example.domain.models.UserData
import com.example.domain.models.UserInfo

interface ProfileRepository {

    suspend fun setProfilePhoto(userImg: Bitmap, uuid: String)

    suspend fun setProfileInfo(userData: UserData, userImg: Bitmap, uuid: String)

    suspend fun updateProfileInfo(email: String, uuid: String)

    suspend fun updateProfileInfo(data: UpdateUserInfo, uuid: String)

    suspend fun getProfileInfo(uuid: String): UserInfo
}