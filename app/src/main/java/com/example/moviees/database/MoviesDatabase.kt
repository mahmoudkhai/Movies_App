package com.example.moviees.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviees.model.topRated.Result

@Database (entities = [Result::class], version = 1)
abstract class MoviesDatabase:RoomDatabase() {
    abstract fun daoInstance():MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MoviesDatabase? = null
          fun getDatabase (context: Context):MoviesDatabase {
             return INSTANCE ?: synchronized(this ){
                 INSTANCE ?: Room.databaseBuilder(context , MoviesDatabase::class.java , "Movie_Database")
                     .fallbackToDestructiveMigration()
                     .build()
             }
         }
    }
}