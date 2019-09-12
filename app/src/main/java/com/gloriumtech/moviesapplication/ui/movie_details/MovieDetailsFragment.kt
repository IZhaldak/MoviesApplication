package com.gloriumtech.moviesapplication.ui.movie_details

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.gloriumtech.moviesapplication.MainActivity

import com.gloriumtech.moviesapplication.R
import com.gloriumtech.moviesapplication.network.NetworkService.PICTURES_BASE_URL
import com.gloriumtech.moviesapplication.pojos.MovieDetailed
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_detais_fragment.*
import java.text.NumberFormat
import java.util.*

class MovieDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = MovieDetailsFragment()
    }

    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_detais_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val mainActivity = activity as MainActivity

        viewModel = ViewModelProviders.of(mainActivity).get(MovieDetailsViewModel::class.java)
        viewModel.movieLiveData.observe(this, Observer { movieDetailed ->
            bindMovieValues(movieDetailed)
        })

        viewModel.movieInserted.observe(this, Observer {
            progress.visibility = View.GONE
            saveBtn.isEnabled = !it
        })
    }

    private fun bindMovieValues(movieDetailed: MovieDetailed) {
        titleTxt.text = movieDetailed.title
        overviewTxt.text = movieDetailed.overview
        voteTxt.text = "${movieDetailed.voteAverage}"
        taglineTxt.text = movieDetailed.tagline
        overviewTxt.text = movieDetailed.overview
        releaseTxt.text = movieDetailed.releaseDate
        statusTxt.text = movieDetailed.status

        val format = NumberFormat.getCurrencyInstance(Locale.US)
        budgetTxt.text = format.format(movieDetailed.budget)

        Picasso.get()
            .load("$PICTURES_BASE_URL${movieDetailed.posterPath}")
            .placeholder(R.mipmap.ic_launcher)
            .into(imageView)

        saveBtn.setOnClickListener {
            progress.visibility = View.VISIBLE
            viewModel.saveMovie()
        }
    }

}
