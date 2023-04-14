package com.kalex.catsapplication.catsList.data.repository

import com.kalex.catsapplication.catsList.data.ApiCats
import com.kalex.catsapplication.catsList.models.CatList
import javax.inject.Inject

class CatsListRespositoryImpl @Inject constructor(
    private val catApi: ApiCats,
) : CatsListRespository {
    override suspend fun getCatsBreeds(): CatList {
        return catApi.getCatsBreeds()
    }
}
