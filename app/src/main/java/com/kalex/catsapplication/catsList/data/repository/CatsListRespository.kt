package com.kalex.catsapplication.catsList.data.repository

import com.kalex.catsapplication.catsList.models.CatItemDto

interface CatsListRespository {
    suspend fun getCatsBreeds(): List<CatItemDto>
}