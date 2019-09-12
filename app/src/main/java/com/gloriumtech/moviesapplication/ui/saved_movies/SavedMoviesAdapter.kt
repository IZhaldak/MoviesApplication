package com.gloriumtech.moviesapplication.ui.saved_movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gloriumtech.moviesapplication.R
import com.gloriumtech.moviesapplication.pojos.MovieDetailed
import com.gloriumtech.moviesapplication.ui.movie_details.MovieDetailsViewModel


class SavedMoviesAdapter(var items: List<MovieDetailed>, private val viewModel: MovieDetailsViewModel):
    RecyclerView.Adapter<SavedMovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedMovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_movie_item,
            parent, false
        )
        return SavedMovieViewHolder(view, viewModel)
    }

    override fun onBindViewHolder(holder: SavedMovieViewHolder, position: Int) {
       holder.bind(items[position])
    }


    override fun getItemCount(): Int = items.size


}

