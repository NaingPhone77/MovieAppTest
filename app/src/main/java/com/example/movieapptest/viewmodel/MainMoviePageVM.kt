package com.example.movieapptest.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapptest.helper.AsyncResourceLiveData
import com.example.movieapptest.helper.Resource
import com.example.movieapptest.model.MovieRepository
import com.example.movieapptest.model.remote.response_data.detail_movies.MovieDetailResponse
import com.example.movieapptest.model.remote.response_data.popular_movies.PopularMovieResponse
import com.example.movieapptest.model.remote.response_data.upcoming_movies.UpcomingMovieResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainMoviePageVM @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    val popularMovie = AsyncResourceLiveData<PopularMovieResponse>()
    val upcomingMovie = AsyncResourceLiveData<UpcomingMovieResponse>()
    val movieDetail = AsyncResourceLiveData<MovieDetailResponse>()

    fun getPopularMovie () {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPopularMovie().collect {
                when(it) {
                    is Resource.Start -> {
                        Log.d("Movie Start" ,it.start.toString())
                    }
                    is Resource.Success -> {
                        popularMovie.postSuccess(it.data)
                    }
                    is Resource.Error -> {
                        popularMovie.postFail(it.throwable,it.errorMessage)
                    }
                    is Resource.Loading -> {
                        popularMovie.postLoading()
                    }
                }
            }
        }
    }

    fun getUpcomingMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getUpcomingMovie().collect {
                when(it) {
                    is Resource.Start -> {
                        Log.d("Movie Start",it.start.toString())
                    }
                    is Resource.Success -> {
                        upcomingMovie.postSuccess(it.data)
                    }
                    is Resource.Error -> {
                        upcomingMovie.postFail(it.throwable,it.errorMessage)
                    }
                    is Resource.Loading -> {
                        upcomingMovie.postLoading()
                    }
                }
            }
        }
    }

    fun getMovieDetail(movieId:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMovieDetail(movieId).collect {
                when (it) {
                    is Resource.Start -> {
                        Log.d("ResourceTest", it.start.toString())
                    }
                    is Resource.Success -> {
                        movieDetail.postSuccess(it.data)
                    }
                    is Resource.Error -> {
                        movieDetail.postFail(it.throwable, it.errorMessage)
                    }
                    is Resource.Loading -> {
                        movieDetail.postLoading()
                    }
                }
            }
        }
    }

}