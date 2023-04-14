package com.kalex.catsapplication.catsList.data.repository

import com.kalex.catsapplication.catsList.models.CatItem

interface CatsListRespository {
    suspend fun getCatsBreeds(): List<CatItem>
}