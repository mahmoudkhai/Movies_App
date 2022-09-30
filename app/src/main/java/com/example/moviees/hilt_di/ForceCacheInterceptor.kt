package com.example.moviees.hilt_di

import android.content.Context
import com.example.moviees.util.Status
import com.example.moviees.util.getNetworkStatus
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class ForceCacheInterceptor  constructor() : Interceptor  {


    override fun intercept(chain: Interceptor.Chain): Response {


        val builder: Request.Builder = chain.request().newBuilder()
        if (getNetworkStatus() == Status.Available) {
            builder.cacheControl(CacheControl.FORCE_CACHE)
        }


        return chain.proceed(builder.build())
}
}