package com.kalex.catsapplication.catsList.data

import com.kalex.catsapplication.catsList.models.CatItem
import com.kalex.catsapplication.catsList.models.CatList
import com.kalex.catsapplication.utils.Constants.API_KEY
import com.kalex.catsapplication.utils.Constants.GET_CATS_BREED_URL
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiCats {
    @Headers("x-api-key:$API_KEY", "Content-Type:application/json")
    @GET(GET_CATS_BREED_URL)
    suspend fun getCatsBreeds(): List<CatItem>
}
