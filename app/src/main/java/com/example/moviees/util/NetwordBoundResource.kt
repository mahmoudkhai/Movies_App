package com.example.moviees.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import com.example.moviees.hilt_di.MyApp
import kotlinx.coroutines.flow.*

// it's single function , not class
inline fun <ResultType, RequestType> networdBoundResourse(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    // we don't pass the whole flow , because we only need one value to make decision
    crossinline shouldFetch: (ResultType) -> Boolean = { true } // default value , in order we don't pass any value
) =
    flow {
        val data = query().first()
        val flow = if (shouldFetch(data)) {
            emit(NetworkResponse.Loading(data)) // this is how we can display the cached data that we got from query while we're getting new data from api

            try {
                saveFetchResult(fetch())
                query().map { NetworkResponse.Success(it) }
            } catch (throwable: Exception) {
                query().map { NetworkResponse.Error(false , errorMessage = throwable.message) }
            }
        } else {
            query().map { NetworkResponse.Success(it) }
        }
        emitAll(flow)
    }
enum class Status {
    Available , Unavailable , Losing , Lost
}


fun getNetworkStatus ():Status {
//    val cm = context.getSystemService(context.contCONNECTIVITY_SERVICE) as ConnectivityManager
    var status = Status.Available
    val callback = object : ConnectivityManager.NetworkCallback(){
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            status = Status.Available
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            status = Status.Lost
        }

        override fun onUnavailable() {
            super.onUnavailable()
            status = Status.Unavailable
        } }
        return status
    }

