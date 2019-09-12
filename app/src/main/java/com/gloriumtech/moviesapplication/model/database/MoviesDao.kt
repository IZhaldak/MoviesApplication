package com.gloriumtech.moviesapplication.model.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gloriumtech.moviesapplication.pojos.MovieDetailed

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    fun getAll() : LiveData<List<MovieDetailed>>

    @Query("SELECT * FROM movies WHERE id = :id ")
    fun loadSingle(id: Long): MovieDetailed?

    @Insert
    fun insert(group: MovieDetailed): Long

    @Update
    fun update(group: MovieDetailed): Int

    @Delete
    fun delete(group: MovieDetailed)
}

/**
 * Returns true when finished
 */
fun MoviesDao.insertIfNotExists(movie: MovieDetailed): Boolean {
    val rowsUpdated = update(movie)
    return if(rowsUpdated == 0) {
        insert(movie)
        true
    } else {
        true
    }


}