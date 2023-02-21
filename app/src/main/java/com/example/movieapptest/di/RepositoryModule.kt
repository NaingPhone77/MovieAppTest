package com.example.movieapptest.di

import com.example.movieapptest.model.MovieRepository
import com.example.movieapptest.model.local.data.MovieLocalDatasource
import com.example.movieapptest.model.remote.MovieDataSource
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    fun provideMovieRepository(
        networkDataSource: MovieDataSource,
        localDatasource: MovieLocalDatasource) : MovieRepository  {

        return MovieRepository(
            networkDataSource,
            localDatasource)

    }
}