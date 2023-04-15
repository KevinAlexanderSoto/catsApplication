package com.kalex.catsapplication.catsList.models.dto

import com.kalex.catsapplication.catsList.models.CatImageResponse

fun CatImageResponse.getImageUrl(): String {
    return url
}