package com.example.moviees.util

import okhttp3.ResponseBody

// using out telling the compiler i'm using this type as a return type
//telling the compiler it's okay to use T or any sub-type of T --- in is in contrast of that ,, in accept any super type of sum-type parameter

// using in telling the compiler i'm using this type as a parameter


//this is example
interface Source<out ITEM > {
    fun next():ITEM
}
fun sourceTest (strings :Source<String>) {
    // but u can't cast sub-type to a superType
    val objects : Source<Any > = strings


}

// in is a like of super keyword , out is a like extends
// like java , kotlin has type erasure , which means that , at runtime you can't query types
// star projection <*> ,, doesn't care about what is inside this <> , put just let me know if it is this type or not

//default T is upper bound to Any? -- so you must treat is as nullable.
sealed class NetworkResponse<out T > {

    data class Success<T> ( val data :T?) : NetworkResponse<T>()

    data class Error (val isNetworkError: Boolean,
                         val errorCode: Int? = null,
                         val errorBody:ResponseBody? = null,
                         val errorMessage:String? = null): NetworkResponse<Nothing>()
    // to show the cached data while fetching new data.
    data class Loading<T> (val data:T? = null) :NetworkResponse<T>()

    fun fetchedData (): T? = if (this is Success) data else null
}
