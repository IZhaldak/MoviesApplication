package com.gloriumtech.moviesapplication.ui.saved_movies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.gloriumtech.moviesapplication.model.database.AppDatabase
import com.gloriumtech.moviesapplication.pojos.MovieDetailed
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SavedMoviesViewModel(app: Application) : AndroidViewModel(app), CoroutineScope{
    private var database: AppDatabase = AppDatabase.getDatabase(app)

    private var moviesDao = database.getMoviesDao()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private  val job: Job = Job()


    suspend fun requestMovies(): LiveData<List<MovieDetailed>> {
        return withContext(Dispatchers.Default) {
                moviesDao.getAll()
            }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
