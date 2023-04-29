package com.kalex.catsapplication.catsList.data

import com.kalex.catsapplication.catsList.models.CatImageResponse
import com.kalex.catsapplication.catsList.models.CatItem
import com.kalex.catsapplication.utils.Constants.API_KEY
import com.kalex.catsapplication.utils.Constants.GET_CATS_BREED_URL
import com.kalex.catsapplication.utils.Constants.GET_CAT_IMG_URL
import com.kalex.catsapplication.utils.Constants.GET_PAGING_CATS_BREED_URL
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiCats {
    @Headers("x-api-key:$API_KEY", "Content-Type:application/json")
    @GET(GET_CATS_BREED_URL)
    suspend fun getCatsBreeds(): List<CatItem>

    @Headers("x-api-key:$API_KEY", "Content-Type:application/json")
    @GET(GET_PAGING_CATS_BREED_URL)
    suspend fun getPagingCatsBreeds(
        @Query("page") page: Int,
    ): List<CatItem>

    @Headers("x-api-key:$API_KEY", "Content-Type:application/json")
    @GET("$GET_CAT_IMG_URL/{id}")
    suspend fun getCatImg(@Path("id") id: String): CatImageResponse
}
