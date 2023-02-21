package com.example.movieapptest.di

import com.example.movieapptest.model.local.data.database.MovieDatabase
import com.example.movieapptest.model.local.data.roomdb.MovieDao
import com.example.movieapptest.model.remote.api.MovieApiCall
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideNetworkCall(retrofit: Retrofit) : MovieApiCall {
        return retrofit.create(MovieApiCall::class.java)
    }

    @Provides
    fun provideLocalData(database: MovieDatabase) : MovieDao {
        return database.movieDao()
    }
}