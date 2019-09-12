package com.gloriumtech.moviesapplication.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gloriumtech.moviesapplication.pojos.Country
import com.gloriumtech.moviesapplication.pojos.Genre
import com.gloriumtech.moviesapplication.pojos.MovieDetailed

@Database(entities = [MovieDetailed::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getMoviesDao(): MoviesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                //Create database
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}