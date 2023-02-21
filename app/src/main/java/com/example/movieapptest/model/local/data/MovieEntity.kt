package com.example.movieapptest.model.local.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_db")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val movieTitle : String,
    val original_title: String,
    val image: String?,
    val movieId: String,
    val rating: String,
    val overview: String,
    val poster_path : String,
    val originalLanguage : String
): java.io.Serializable