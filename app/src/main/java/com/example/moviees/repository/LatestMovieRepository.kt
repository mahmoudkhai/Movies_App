package com.example.moviees.repository

import com.example.moviees.model.movieDetails.MovieDetailsResponse
import com.example.moviees.model.topRated.Result
import com.example.moviees.model.topRated.TopRatedMoviesResponse
import com.example.moviees.util.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface LatestMovieRepository {
    fun getTopRatedMovies():Flow<NetworkResponse<TopRatedMoviesResponse?>>
    fun fetchMovieDetails (movieId:Int): Flow<NetworkResponse<MovieDetailsResponse?>>
}