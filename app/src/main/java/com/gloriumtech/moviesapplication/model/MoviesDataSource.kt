package com.gloriumtech.moviesapplication.model

import androidx.paging.PageKeyedDataSource
import com.gloriumtech.moviesapplication.network.NetworkService
import com.gloriumtech.moviesapplication.pojos.Movie
import com.gloriumtech.moviesapplication.pojos.MoviesPage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesDataSource(val scope: CoroutineScope) : PageKeyedDataSource<Long, Movie>() {


    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, Movie>) {
        scope.launch(Dispatchers.IO) {

        val page: MoviesPage = NetworkService.getApi().getPopularMovies(1)
            launch(Dispatchers.Main) {
                callback.onResult(page.results, null, 2)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Movie>) {
        scope.launch(Dispatchers.IO) {

            val page: MoviesPage = NetworkService.getApi().getPopularMovies(2)
            launch(Dispatchers.Main) {
                callback.onResult(page.results, params.key + 1)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Movie>) {
    }

}