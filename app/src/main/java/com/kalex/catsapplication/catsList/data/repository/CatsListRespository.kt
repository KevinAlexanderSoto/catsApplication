package com.kalex.catsapplication.catsList.data.repository

import com.kalex.catsapplication.catsList.models.CatItemDto

interface CatsListRepository {
    suspend fun getCatsBreeds(): List<CatItemDto>
    suspend fun getPagingCatsBreeds(page: Int): List<CatItemDto>
    suspend fun getCatImage(id: String): String
}