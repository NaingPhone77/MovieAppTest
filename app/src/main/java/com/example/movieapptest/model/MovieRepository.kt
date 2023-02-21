package com.example.movieapptest.model

import androidx.lifecycle.LiveData
import com.example.movieapptest.helper.Resource
import com.example.movieapptest.model.local.data.MovieEntity
import com.example.movieapptest.model.local.data.MovieLocalDatasource
import com.example.movieapptest.model.remote.MovieDataSource
import com.example.movieapptest.model.remote.response_data.detail_movies.MovieDetailResponse
import com.example.movieapptest.model.remote.response_data.popular_movies.PopularMovieResponse
import com.example.movieapptest.model.remote.response_data.upcoming_movies.UpcomingMovieResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val networkDataSource: MovieDataSource,
    private val localDatasource: MovieLocalDatasource
) {

    suspend fun getPopularMovie() : Flow<Resource<PopularMovieResponse>>{
        return networkDataSource.getPopularMovieFlow()
    }

    suspend fun getUpcomingMovie() : Flow<Resource<UpcomingMovieResponse>>{
        return networkDataSource.getUpcomingMovieFlow()
    }

    suspend fun getMovieDetail(movieId:Int) : Flow<Resource<MovieDetailResponse>> {
        return networkDataSource.getMovieDetailFlow(movieId)
    }

    suspend fun deleteMovieData(movieEntity: MovieEntity){
        return localDatasource.deleteMovie(movieEntity)
    }

    suspend fun insertMovieData(movieDatabaseEntity: MovieEntity){
        return localDatasource.insertMovieData(movieDatabaseEntity)
    }

    val readAllMovieData : LiveData<List<MovieEntity>> = localDatasource.readAllMovieData
}