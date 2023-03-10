package com.example.data.repositories

import com.example.data.mappers.ProfileInfoMapper
import com.example.data.models.UserInfoDto
import com.example.data.util.USERS_DB_PATH
import com.example.domain.models.UserInfo
import com.example.domain.repositories.ProfileRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : ProfileRepository {

    override suspend fun setProfileInfo(email: String, uuid: String) {
        val data: HashMap<String, String> = hashMapOf(
            "username" to email.takeWhile { it != '@' },
            "firstName" to "",
            "lastName" to "",
            "userImg" to "",
            "email" to email,
            "telephone" to ""
        )

        firestore.collection(USERS_DB_PATH).document(uuid).set(data).await()
    }

    override suspend fun getProfileInfo(uuid: String): UserInfo {
        val result = firestore.collection(USERS_DB_PATH)
            .document(uuid)
            .get()
            .await()
            .toObject(UserInfoDto::class.java)

        return ProfileInfoMapper.toDomainModel(result)
    }
}