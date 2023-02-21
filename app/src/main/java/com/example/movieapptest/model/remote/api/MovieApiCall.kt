package com.example.movieapptest.model.remote.api

import com.example.movieapptest.model.remote.response_data.detail_movies.MovieDetailResponse
import com.example.movieapptest.model.remote.response_data.popular_movies.PopularMovieResponse
import com.example.movieapptest.model.remote.response_data.upcoming_movies.UpcomingMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiCall {

    @GET("3/movie/popular?api_key=3a00764f2116c32aac2a3b738a4b4b17&language=en-US&page=1")
    suspend fun getPopularMovieData() : Response<PopularMovieResponse>

    @GET("3/movie/upcoming?api_key=3a00764f2116c32aac2a3b738a4b4b17&language=en-US&page=1")
    suspend fun getUpcomingMovieData() : Response<UpcomingMovieResponse>

    @GET("3/movie/{movie_id}?api_key=3a00764f2116c32aac2a3b738a4b4b17&language=en-US")
    suspend fun getMovieDetail(@Path("movieId") movieId : Int) : Response<MovieDetailResponse>
}