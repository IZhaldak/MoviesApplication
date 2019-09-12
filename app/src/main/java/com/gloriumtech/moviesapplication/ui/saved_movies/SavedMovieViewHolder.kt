package com.gloriumtech.moviesapplication.ui.saved_movies

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.gloriumtech.moviesapplication.R
import com.gloriumtech.moviesapplication.network.NetworkService
import com.gloriumtech.moviesapplication.pojos.MovieDetailed
import com.gloriumtech.moviesapplication.ui.movie_details.MovieDetailsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_movie_item.view.*

class SavedMovieViewHolder (view: View, private val viewModel: MovieDetailsViewModel) :
    RecyclerView.ViewHolder(view) {

    private val imageView: ImageView = view.imageView
    private val titleTxt: TextView = view.titleTxt
    private val overviewTxt: TextView = view.overviewTxt
    private val voteTxt: TextView = view.voteTxt
    private val card: CardView = view.card

    fun bind(movie: MovieDetailed) {
        titleTxt.text = movie.title
        overviewTxt.text = movie.overview
        voteTxt.text = "${movie.voteAverage}"

        Picasso.get()
            .load("${NetworkService.PICTURES_BASE_URL}${movie.posterPath}")
            .placeholder(R.mipmap.ic_launcher)
            .into(imageView)

        card.setOnClickListener {
            viewModel.movieLiveData.value = movie
            val navController = it.findNavController()
            navController.navigate(R.id.movieDetailsFragment)
        }
    }
}