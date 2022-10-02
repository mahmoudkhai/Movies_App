package com.example.moviees.hilt_di

import android.content.Context
import androidx.room.Room
import com.example.moviees.database.MoviesDatabase
import com.example.moviees.repository.LatestMovieRepository
import com.example.moviees.repository.MoviesRepository
import com.example.moviees.api.MovieApi
import com.example.moviees.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
object Module {

//    @Singleton
//    @Provides
    fun getApiInstance(): MovieApi {
//        val okHttpClient = OkHttpClient().newBuilder() //This builds a client that shares the same connection pool, thread pools, and configuration.
//            .readTimeout(4000 , TimeUnit.MICROSECONDS)
//            .writeTimeout(4000 , TimeUnit.MICROSECONDS)
//            .addNetworkInterceptor(ChachInterceptor()) // Network Interceptor
//            .addInterceptor(ForceCacheInterceptor()) // Application Interceptor
//            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHttpClient)
            .build()
            .create(MovieApi::class.java)
    }



//    @Singleton
//    @Provides
//    fun getRepositoryInstance(api: MovieApi, database: MoviesDatabase): LatestMovieRepository =
//        MoviesRepository(database, api)
////
//    @Singleton
//    @Provides
//    fun movieDataBaseInstance(@ApplicationContext context: Context): MoviesDatabase {
//        return synchronized(this) {
//            Room.databaseBuilder(context, MoviesDatabase::class.java, "Movies_Database")
//                .fallbackToDestructiveMigration() // add this in order not to loss data if the schema changed.
//                .build()
//        }
//    }

}
