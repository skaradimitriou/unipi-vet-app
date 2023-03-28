package com.example.data.mappers

import com.example.data.models.DashboardModelDto
import com.example.data.models.FeaturedItemDto
import com.example.data.util.toNotNull
import com.example.domain.models.DashboardModel
import com.example.domain.models.FeaturedItem
import com.example.domain.models.FeaturedItemParent
import com.example.domain.models.VetItemParent

object DashboardMapper : Mapper<DashboardModelDto?, DashboardModel> {

    override fun toDomainModel(dto: DashboardModelDto?): DashboardModel {
        return DashboardModel(
            userInfo = ProfileInfoMapper.toDomainModel(dto?.userInfo),
            featured = FeaturedItemParent(dto?.featured.toDomainModel()),
            popularVets = VetItemParent(VetMapper.toDomainModel(dto?.popularVets))
        )
    }

    private fun List<FeaturedItemDto>?.toDomainModel() = this?.map {
        it.toDomainModel()
    } ?: listOf()

    private fun FeaturedItemDto?.toDomainModel() = FeaturedItem(
        image = this?.image.toNotNull(),
        text = this?.text.toNotNull()
    )
}