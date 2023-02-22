package com.example.data.mappers

import com.example.data.models.UserInfoDto
import com.example.data.util.toNotNull
import com.example.domain.models.UserInfo

object ProfileInfoMapper : Mapper<UserInfoDto?, UserInfo> {

    override fun toDomainModel(dto: UserInfoDto?): UserInfo = UserInfo(
        firstName = dto?.firstName.toNotNull(),
        lastName = dto?.lastName.toNotNull(),
        telephone = dto?.telephone.toNotNull(),
        email = dto?.email.toNotNull(),
        userImg = dto?.userImg.toNotNull(),
        username = dto?.username.toNotNull()
    )
}