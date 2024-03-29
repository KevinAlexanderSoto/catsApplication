package com.kalex.catsapplication.catsList.models.dto

import com.kalex.catsapplication.catsList.models.CatItem
import com.kalex.catsapplication.catsList.models.CatItemDto

fun CatItem.convertToCatDto(): CatItemDto {
    return CatItemDto(
        id = id,
        name = name,
        intelligence = intelligence,
        origin = origin,
        imgId = reference_image_id,
        imgUrl = "",
    )
}
