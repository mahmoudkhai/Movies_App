@file:Suppress("UNCHECKED_CAST")

package com.example.moviees.util

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviees.adapter.BaseAdapter
import com.example.moviees.adapter.MovieItemAdapter
import com.example.moviees.model.topRated.Result
import com.example.moviees.model.topRated.TopRatedMoviesResponse

@BindingAdapter(value = ["app:showWhenLoading"])
fun <T> showWhenLoading(view: View, state: NetworkResponse<T>) =
    if (state is NetworkResponse.Loading) view.visibility = View.VISIBLE
    else view.visibility = View.GONE


//@BindingAdapter(value = ["app:showWhenSucess"])
//fun <T> showWhenSuccess(view: View, state: NetworkResponse<T>) {
//    if (state is NetworkResponse.Success) {
//        view.visibility = View.VISIBLE
//    } else view.visibility = View.GONE
//}


@BindingAdapter(value = ["app:showWhenError"])
fun <T> showWhenError(view: TextView, state: NetworkResponse<T>) =
    if (state is NetworkResponse.Error) {
        view.visibility = View.VISIBLE
//        view.text = state.errorMessage
    } else view.visibility = View.GONE


@BindingAdapter(value = ["app:imageUrl"])
fun setImageFromUrl(view: ImageView, url: String?) =
    Glide.with(view).load(url).centerCrop().into(view)


@BindingAdapter(value = ["app:items"])
fun <T> setRecyclarItems(view: RecyclerView, items: List<T>?) {
    (view.adapter as BaseAdapter<T>).setNewData(items ?: emptyList())
}