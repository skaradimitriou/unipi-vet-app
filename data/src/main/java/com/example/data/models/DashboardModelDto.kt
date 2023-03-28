package com.example.data.models

data class DashboardModelDto(
    val userInfo: UserInfoDto? = null,
    val featured: List<FeaturedItemDto>? = null,
    val popularVets: List<VetDto>? = null
)

data class FeaturedItemDto(
    val image: String? = null,
    val text: String? = null
)