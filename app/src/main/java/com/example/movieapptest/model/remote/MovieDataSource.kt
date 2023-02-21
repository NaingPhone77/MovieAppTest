package com.example.movieapptest.model.remote

import com.example.movieapptest.helper.Resource
import com.example.movieapptest.model.remote.api.MovieApiCall
import com.example.movieapptest.model.remote.response_data.detail_movies.MovieDetailResponse
import com.example.movieapptest.model.remote.response_data.popular_movies.PopularMovieResponse
import com.example.movieapptest.model.remote.response_data.upcoming_movies.UpcomingMovieResponse
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDataSource @Inject constructor(
    private val movieApiCall: MovieApiCall
) : BaseDataSource(){

    suspend fun getPopularMovieFlow() : kotlinx.coroutines.flow.Flow<Resource<PopularMovieResponse>>{
        return flow {
            emit(Resource.Start())
            emit(Resource.Loading())
            emit(getResultOrError { movieApiCall.getPopularMovieData() })
        }
    }

    suspend fun getUpcomingMovieFlow() : kotlinx.coroutines.flow.Flow<Resource<UpcomingMovieResponse>> {
        return flow{
            emit(Resource.Start())
            emit(Resource.Loading())
            emit(getResultOrError { movieApiCall.getUpcomingMovieData() })
        }
    }

    suspend fun getMovieDetailFlow(movieId : Int) : kotlinx.coroutines.flow.Flow<Resource<MovieDetailResponse>> {
        return flow{
            emit(Resource.Start())
            emit(Resource.Loading())
            emit(getResultOrError { movieApiCall.getMovieDetail(movieId) })
        }
    }


}