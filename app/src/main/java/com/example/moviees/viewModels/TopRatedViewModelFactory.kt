package com.example.moviees.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviees.api.MovieApi
import com.example.moviees.repository.LatestMovieRepository

class TopRatedViewModelFactory(private val repository: LatestMovieRepository ) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TopRatedMoviesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TopRatedMoviesViewModel(repository) as T
        }
        throw IllegalAccessException("Unknown ViewModel class")
    }
}