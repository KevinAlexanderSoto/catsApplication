package com.kalex.catsapplication

import com.kalex.catsapplication.catsList.data.ApiCats
import com.kalex.catsapplication.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CatsModule {
    @Provides
    @Singleton
    fun provideDogApi(): ApiCats = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(ApiCats::class.java)
}
