package com.example.data.repositories

import android.graphics.Bitmap
import com.example.data.mappers.ProfileInfoMapper
import com.example.data.models.UserInfoDto
import com.example.data.util.USERS_DB_PATH
import com.example.data.util.compressBitmap
import com.example.domain.models.UpdateUserInfo
import com.example.domain.models.UserData
import com.example.domain.models.UserInfo
import com.example.domain.repositories.ProfileRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class ProfileRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val storage: StorageReference
) : ProfileRepository {

    override suspend fun setProfilePhoto(userImg: Bitmap, uuid: String) {
        val storageRef = storage.child("pics/$uuid")
        val image = userImg.compressBitmap()
        val upload = storageRef.putBytes(image).await()
        val downloadUrl = upload.metadata?.reference?.downloadUrl?.await().toString()

        val data: HashMap<String, Any> = hashMapOf(
            "userImg" to downloadUrl
        )

        firestore.collection(USERS_DB_PATH).document(uuid).update(data).await()
    }

    override suspend fun setProfileInfo(
        userData: UserData,
        userImg: Bitmap,
        uuid: String
    ) {
        val storageRef = storage.child("pics/$uuid")
        val image = userImg.compressBitmap()
        val upload = storageRef.putBytes(image).await()
        val downloadUrl = upload.metadata?.reference?.downloadUrl?.await().toString()

        val data: HashMap<String, Any> = hashMapOf(
            "username" to userData.username,
            "firstName" to userData.firstName,
            "lastName" to userData.lastName,
            "userImg" to downloadUrl,
            "telephone" to userData.telephone
        )

        firestore.collection(USERS_DB_PATH).document(uuid).update(data).await()
    }

    override suspend fun updateProfileInfo(email: String, uuid: String) {
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

    override suspend fun updateProfileInfo(data: UpdateUserInfo, uuid: String) {
        val updatedData: HashMap<String, Any> = hashMapOf(
            "firstName" to data.firstName,
            "lastName" to data.lastName,
            "username" to data.username,
            "telephone" to data.telephone
        )

        firestore.collection(USERS_DB_PATH).document(uuid).update(updatedData).await()
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