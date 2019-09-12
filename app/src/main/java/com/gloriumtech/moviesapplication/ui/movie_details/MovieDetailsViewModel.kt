package com.gloriumtech.moviesapplication.ui.movie_details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.gloriumtech.moviesapplication.model.database.AppDatabase
import com.gloriumtech.moviesapplication.model.database.insertIfNotExists
import com.gloriumtech.moviesapplication.network.NetworkService
import com.gloriumtech.moviesapplication.pojos.MovieDetailed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MovieDetailsViewModel(app: Application) : AndroidViewModel(app), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val job: Job = Job()
    val dataBase = AppDatabase.getDatabase(app)
    val moviesDao = dataBase.getMoviesDao()
    val movieInserted: MutableLiveData<Boolean> = MutableLiveData(false)


    var movieLiveData: MutableLiveData<MovieDetailed> = MutableLiveData()
    var movieId: Long = 0
        set(value) {
            field = value
            requestMovieDetails(value)
        }

    private fun requestMovieDetails(movieId: Long) {
        launch(Dispatchers.IO) {
            val movieDetails = NetworkService.getApi().getMovieDetails(movieId)
            launch(Dispatchers.Main) {
                movieInserted.value = false
                movieLiveData.value = movieDetails
            }
        }
    }
    fun saveMovie() {
        launch(Dispatchers.IO) {
            val inserted =  moviesDao.insertIfNotExists(movieLiveData.value!!)
            launch(Dispatchers.Main) {
                movieInserted.value = inserted
            }
        }
    }
}
