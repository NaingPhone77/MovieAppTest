package com.example.movieapptest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapptest.model.MovieRepository
import com.example.movieapptest.model.local.data.MovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieRoomVM @Inject constructor(
    private val repository: MovieRepository
) : ViewModel(){

    val readAllMovieData : LiveData<List<MovieEntity>> = repository.readAllMovieData

    fun addMovie(movieEntity: MovieEntity) {
        viewModelScope.launch {
            repository.insertMovieData(movieEntity)
        }
    }

    fun deleteMovie(movieEntity: MovieEntity) {
        viewModelScope.launch {
            repository.deleteMovieData(movieEntity)
        }
    }
}