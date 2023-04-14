package com.kalex.catsapplication.catsList.data.repository

import com.kalex.catsapplication.catsList.models.CatList

interface CatsListRespository {
    suspend fun getCatsBreeds(): CatList
}