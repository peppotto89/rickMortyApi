package com.fabricktest.rickmortyapi

import com.fabricktest.rickmortyapi.data.ApiService
import com.fabricktest.rickmortyapi.data.repository.CharacterRepositoryImpl
import com.fabricktest.rickmortyapi.viewmodel.CharacterViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(apiService: ApiService): CharacterRepositoryImpl {
        return CharacterRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideCharacterViewModel(repository: CharacterRepositoryImpl): CharacterViewModel {
        return CharacterViewModel(repository)
    }


}