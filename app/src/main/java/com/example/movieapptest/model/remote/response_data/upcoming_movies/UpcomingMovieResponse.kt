package com.example.movieapptest.model.remote.response_data.upcoming_movies

import com.example.movieapptest.model.remote.response_data.Result

data class UpcomingMovieResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)