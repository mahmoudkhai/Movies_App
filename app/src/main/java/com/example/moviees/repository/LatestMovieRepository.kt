package com.example.moviees.repository

import com.example.moviees.model.topRated.Result
import com.example.moviees.model.topRated.TopRatedMoviesResponse
import com.example.moviees.util.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface LatestMovieRepository {
    fun getTopRatedMovies():Flow<NetworkResponse<TopRatedMoviesResponse?>>
}