package com.example.movieapptest.model.remote.response_data.popular_movies

import com.example.movieapptest.model.remote.response_data.Result

data class PopularMovieResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)