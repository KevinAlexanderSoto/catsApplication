package com.kalex.catsapplication.catsList.data

import com.kalex.catsapplication.Constants.API_KEY
import com.kalex.catsapplication.Constants.GET_CATS_BREED_URL
import com.kalex.catsapplication.catsList.models.CatList
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiCats {
    @Headers("x-api-key:${API_KEY}")
    @GET(GET_CATS_BREED_URL)
    suspend fun getCatsBreeds(): CatList
}