package com.example.moviees.model.topRated

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity

data class Result(
    val adult: Boolean,
    val backdrop_path: String,
//    val genre_ids: List<Int>,
    @PrimaryKey (autoGenerate = true)
    val id: Int,
    val original_language: String,
    @ColumnInfo (name = "movie_title")
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)