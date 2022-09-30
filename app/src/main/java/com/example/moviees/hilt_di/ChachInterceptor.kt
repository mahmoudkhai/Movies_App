package com.example.moviees.hilt_di

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class ChachInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val response = chain.proceed(chain.request()) // this is where all HTTP work happens
        val cacheControl = CacheControl.Builder()
            .maxAge(10, TimeUnit.DAYS)
            .build()

        return response.newBuilder().header("Cache_Control", cacheControl.toString()).build()
    }

}
