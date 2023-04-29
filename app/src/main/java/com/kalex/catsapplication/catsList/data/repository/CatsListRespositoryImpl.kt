package com.kalex.catsapplication.catsList.data.repository

import com.kalex.catsapplication.catsList.data.ApiCats
import com.kalex.catsapplication.catsList.models.CatItemDto
import com.kalex.catsapplication.catsList.models.dto.convertToCatDto
import com.kalex.catsapplication.catsList.models.dto.getImageUrl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class CatsListRepositoryImpl @Inject constructor(
    private val catApi: ApiCats,
) : CatsListRepository {
    override suspend fun getCatsBreeds(): List<CatItemDto> {
        return coroutineScope {
            val nativeList = async(Dispatchers.IO) {
                catApi.getCatsBreeds()
            }
            nativeList.await().map {
                it.convertToCatDto()
            }
        }
    }

    override suspend fun getPagingCatsBreeds(page: Int): List<CatItemDto> {
        return coroutineScope {
            val nativeList = async(Dispatchers.IO) {
                catApi.getPagingCatsBreeds(page)
            }
            val catList = async(Dispatchers.Default) {
                nativeList.await().map {
                    it.convertToCatDto()
                }
            }

            catList.await().apply {
                map {
                    it.imgUrl = catApi.getCatImg(it.imgId ?: "NZpO4pU56M").getImageUrl()
                }
            }
        }
    }

    override suspend fun getCatImage(id: String): String {
        return catApi.getCatImg(id).getImageUrl()
    }
}
