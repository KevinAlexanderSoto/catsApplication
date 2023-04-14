package com.kalex.catsapplication.catsList.data.repository

import com.kalex.catsapplication.catsList.data.ApiCats
import com.kalex.catsapplication.catsList.models.CatItem
import javax.inject.Inject

class CatsListRespositoryImpl @Inject constructor(
    private val catApi: ApiCats,
) : CatsListRespository {
    override suspend fun getCatsBreeds(): List<CatItem> {
        val result = catApi.getCatsBreeds()
        return result
    }
}
