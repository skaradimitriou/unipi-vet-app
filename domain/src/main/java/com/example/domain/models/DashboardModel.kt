package com.example.domain.models

data class DashboardModel(
    var userInfo: UserInfo,
    val featured: FeaturedItemParent,
    var popularVets: VetItemParent
) {
    fun toUiData(): List<UiModel> = listOf(
        userInfo,
        featured,
        popularVets
    )
}

data class FeaturedItemParent(
    val items: List<FeaturedItem>
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is FeaturedItemParent -> items == obj.items
        else -> false
    }
}

data class VetItemParent(
    var items: List<Vet>
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is VetItemParent -> items == obj.items
        else -> false
    }
}

data class FeaturedItem(
    val image: String,
    val text: String
) : UiModel {
    override fun equalsContent(obj: UiModel): Boolean = when (obj) {
        is FeaturedItem -> image == obj.image && text == obj.text
        else -> false
    }
}