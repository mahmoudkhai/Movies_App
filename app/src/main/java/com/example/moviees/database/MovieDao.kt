package com.example.moviees.database

import androidx.room.*
import com.example.moviees.model.topRated.Result
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

     @Insert (onConflict = OnConflictStrategy.IGNORE)
      suspend fun cacheMovies (results: List<Result>?)

      //Flow here acts as observable , it'll emit any new data entered in database ,, Flow is cold , it starts emitting values when we collect
     @Query("SELECT * FROM Result")
      fun getCachedMovies(): Flow<List<Result>>

      @Query ("DELETE FROM Result")
      suspend fun deleteAllCachedMovies()


}