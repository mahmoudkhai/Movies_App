package com.example.moviees.repository

import android.util.Log
import android.widget.Toast
import com.example.moviees.database.MoviesDatabase
import com.example.moviees.model.topRated.Result
import com.example.moviees.api.MovieApi
import com.example.moviees.model.movieDetails.MovieDetailsResponse
import com.example.moviees.model.topRated.TopRatedMoviesResponse
import com.example.moviees.util.Constants
import com.example.moviees.util.NetworkResponse
import com.example.moviees.util.networdBoundResourse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

import javax.inject.Inject

class MoviesRepository
//@Inject constructor(
    ( private val database: MoviesDatabase,
    private val api: MovieApi
) :
    LatestMovieRepository {
    private val dao = database.daoInstance()

//    override fun getTopRatedMovies(): Flow<NetworkResponse<TopRatedMoviesResponse?>> =
//        networdBoundResourse<List<Result>, List<Result>?>(
//            query = {
//                dao.getCachedMovies()
//            }, fetch = {
//                api.getTopRated().body()?.results
//            }, saveFetchResult = {
//                dao.deleteAllCachedMovies()
//                dao.cacheMovies(it)
//            }
//        )

    private fun <T> wrapWithFlow(function: suspend (apiKey: String) -> Response<T?>): Flow<NetworkResponse<T?>> {
        return flow {
            emit(NetworkResponse.Loading())
            delay(1000)
            try {
                println( "now trying to fetch data")
                val response = function(Constants.API_KEY)
                if (response.isSuccessful) {
                    println("data fetched successfully")
                    emit(NetworkResponse.Success(response.body()))
                    println("data emitted successfully")
                }else {
                    println("error happened")
                    emit(NetworkResponse.Error(isNetworkError = false , errorBody = response.errorBody()))
                    println("error emmitted")
                }
            } catch (throwable : Throwable) {
                println("i catch throwable")
                when(throwable){
                   is HttpException -> emit(NetworkResponse.Error(isNetworkError = false , throwable.code() , throwable.response()?.errorBody()))

                   is UnknownHostException -> emit(NetworkResponse.Error(isNetworkError = false , errorMessage = throwable.message))

                   else -> emit(NetworkResponse.Error (isNetworkError = false , errorMessage = "error"))
               }
            }
//            finally {
//                emit(NetworkResponse.Error(false , errorMessage = "Something Wrong"))
//            }
        }
    }

    override fun getTopRatedMovies(): Flow<NetworkResponse<TopRatedMoviesResponse?>> {
        return wrapWithFlow { api.getTopRated(Constants.API_KEY) }
    }

    override fun fetchMovieDetails(movieId: Int): Flow<NetworkResponse<MovieDetailsResponse?>> {
        return wrapWithFlow { api.movieDetailsRequest(movieId = movieId , apiKey = Constants.API_KEY) }
    }

//    fun test(): Flow<NetworkResponse<TopRatedMoviesResponse?>> {
//
//        // flow isn't state aware , if the configuration changed it starts over
//        return flow {
//            emit(NetworkResponse.Loading())
//            try {
//                val result = api.getTopRated(Constants.API_KEY)
//
//                if (result.isSuccessful) {
//                    dao.cacheMovies(result.body()!!.results)
//                    emit(NetworkResponse.Success(result.body()))
//                } else {
//                    emit(NetworkResponse.Error(result.message()))
//                }
//            } catch (e: Exception) {
//                emit(NetworkResponse.Error(e.message.toString()))
//            }
//        }
//    }
}


