package com.kalex.catsapplication.catsList.models

data class CatImageResponse(
    var id: String,
    var url: String,
    var breeds: List<CatItem>,
    var width: Int,
    var height: Int
)
