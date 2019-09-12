package com.gloriumtech.moviesapplication.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.gloriumtech.moviesapplication.R
import com.gloriumtech.moviesapplication.pojos.Movie
import com.gloriumtech.moviesapplication.ui.movie_details.MovieDetailsViewModel


class MoviesAdapter(val viewModel: MovieDetailsViewModel) :
    PagedListAdapter<Movie, MovieViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_movie_item,
            parent, false
        )
        return MovieViewHolder(view, viewModel)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}

val DIFF_CALLBACK: DiffUtil.ItemCallback<Movie> = object : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return true
    }
}