package com.kalex.catsapplication.catsList.models

data class CatItemDto(
    val id: String,
    val name: String,
    val intelligence: Int,
    val origin: String,
    var imgId: String?,
    var imgUrl: String,
)
