package com.kalex.catsapplication.catsList.di

import com.kalex.catsapplication.catsList.data.ApiCats
import com.kalex.catsapplication.catsList.data.repository.CatsListRespository
import com.kalex.catsapplication.catsList.data.repository.CatsListRespositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CatsListModule {

    @Provides
    @Singleton
    fun provideCatsRepository(api: ApiCats): CatsListRespository {
        return CatsListRespositoryImpl(api)
    }
}