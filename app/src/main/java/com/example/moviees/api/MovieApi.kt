package com.example.moviees.api

import com.example.moviees.model.latestMovie.LatestMoviesResponse
import com.example.moviees.model.movieDetails.MovieDetailsResponse
import com.example.moviees.model.movieImages.MovieImagesResponse
import com.example.moviees.model.topRated.TopRatedMoviesResponse
import com.example.moviees.util.Constants
import retrofit2.Response
import retrofit2.http.*

interface MovieApi {

    @GET("/movie/latest")
    suspend fun getLatestMovie(
        @Query("api_key") apiKey:String
    ): Response<LatestMoviesResponse?>

    @GET("/movie/{movie_id}")
    suspend fun movieDetailsRequest(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Path("movie_id") movieId: Int,
    ):Response<MovieDetailsResponse>

    @GET("/movie/{movie_id}/similar")
    suspend fun similarMovies(@Path("movie_id") movieId: Int , @Query("api_key") apiKey: String = Constants.API_KEY,)

    @FormUrlEncoded
    @POST("movie/{movie_id}/rating")
    suspend fun rateMovie(
        @Path ("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query ("guest_session_id") gustSessionId :String?,
        @Query("session_id") sessionId :String?,
        @Field("value") stars: Int,

    )

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("api_key") apiKey: String = Constants.API_KEY,
//        @Query("page") pageNumber:Int
    ):Response<TopRatedMoviesResponse?>

    @GET("movie/popular")
    suspend fun popularMoviesRequest (@Query ("api_key") apiKey: String = Constants.API_KEY,)

    @GET ("movie/{movie_id}/images")
    suspend fun movieImages (@Path ("movie_id") movieId: Int ,
                             @Query("api_key") apiKey: String = Constants.API_KEY,):Response<MovieImagesResponse>
}