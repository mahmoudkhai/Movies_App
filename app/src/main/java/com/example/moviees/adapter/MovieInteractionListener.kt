package com.example.moviees.adapter

import com.example.moviees.model.topRated.Result

interface MovieInterActionListener : BaseListener {
    fun onMovieClicked(movie: Result)
}