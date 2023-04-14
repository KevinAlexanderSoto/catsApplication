package com.kalex.catsapplication.catsList.data.repository

import com.kalex.catsapplication.catsList.data.ApiCats
import com.kalex.catsapplication.catsList.models.CatItem
import com.kalex.catsapplication.catsList.models.CatItemDto
import com.kalex.catsapplication.catsList.models.dto.ConvertToCatDto
import javax.inject.Inject

class CatsListRespositoryImpl @Inject constructor(
    private val catApi: ApiCats,
) : CatsListRespository {
    override suspend fun getCatsBreeds(): List<CatItemDto> {
        return catApi.getCatsBreeds().map { it.ConvertToCatDto() }
    }
}
