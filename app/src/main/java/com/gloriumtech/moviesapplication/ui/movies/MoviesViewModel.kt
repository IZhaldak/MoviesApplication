package com.gloriumtech.moviesapplication.ui.movies

import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.gloriumtech.moviesapplication.model.MoviesDataSource
import com.gloriumtech.moviesapplication.pojos.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class MoviesViewModel : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private  val job: Job = Job()

    private val config: PagedList.Config = PagedList.Config.Builder()
        .setPageSize(2)
        .setEnablePlaceholders(false)
        .build()

    val moviesLiveData = initializedPagedListBuilder(config).build()

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Long, Movie> {

        val dataSourceFactory = object : DataSource.Factory<Long, Movie>() {
            override fun create(): DataSource<Long, Movie> {
                return MoviesDataSource(this@MoviesViewModel)
            }
        }
        return LivePagedListBuilder<Long, Movie>(dataSourceFactory, config)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }



}
