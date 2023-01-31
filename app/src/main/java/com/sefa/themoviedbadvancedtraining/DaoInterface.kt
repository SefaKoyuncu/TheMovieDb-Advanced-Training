package com.sefa.themoviedbadvancedtraining

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.*


@Dao
interface DaoInterface
{
    @Insert
    suspend fun addMovie(movies: Movies)

    @Query("SELECT * FROM movies")
    suspend fun getMovies() : List<Movies>
}