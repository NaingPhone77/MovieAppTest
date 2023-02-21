package com.example.movieapptest.model.local.data.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.movieapptest.model.local.data.MovieEntity


@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(movieEntity: MovieEntity)

    @Query("SELECT * FROM movie_db ORDER BY id ASC")
    fun readAllMovieData() : LiveData<List<MovieEntity>>

    @Delete
    suspend fun deleteMovie(movieEntity: MovieEntity)
}