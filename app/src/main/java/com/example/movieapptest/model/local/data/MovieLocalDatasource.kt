package com.example.movieapptest.model.local.data

import androidx.lifecycle.LiveData
import com.example.movieapptest.model.local.data.MovieEntity
import com.example.movieapptest.model.local.data.roomdb.MovieDao
import javax.inject.Inject


class MovieLocalDatasource @Inject constructor (private val movieDao : MovieDao) {
    suspend fun insertMovieData(movieEntity: MovieEntity){
        movieDao.insertData(movieEntity)
    }

    suspend fun deleteMovie(movieEntity: MovieEntity){
        movieDao.deleteMovie(movieEntity)
    }

    val readAllMovieData : LiveData<List<MovieEntity>> =movieDao.readAllMovieData()
}