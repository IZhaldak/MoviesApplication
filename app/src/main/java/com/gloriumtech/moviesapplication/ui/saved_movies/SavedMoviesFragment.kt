package com.gloriumtech.moviesapplication.ui.saved_movies

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gloriumtech.moviesapplication.MainActivity

import com.gloriumtech.moviesapplication.R
import com.gloriumtech.moviesapplication.ui.movie_details.MovieDetailsViewModel
import kotlinx.android.synthetic.main.movies_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedMoviesFragment : Fragment() {

    companion object {
        fun newInstance() = SavedMoviesFragment()
    }

    private lateinit var viewModel: SavedMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SavedMoviesViewModel::class.java)

        toolbar_title.text = context?.getString(R.string.saved_movies)
        button_saved.text = context?.getString(R.string.to_recent_movies)

        button_saved.setOnClickListener {
            val navController = it.findNavController()
            navController.navigate(R.id.moviesFragment)
        }
        recycler.layoutManager = LinearLayoutManager(context)

        val mainActivity = activity as MainActivity
        val detailsViewModel = ViewModelProviders.of(mainActivity).get(MovieDetailsViewModel::class.java)

        progress.visibility = View.GONE

        viewModel.launch(Dispatchers.Main) {
            viewModel.requestMovies().observe(this@SavedMoviesFragment, Observer {
                recycler.adapter = SavedMoviesAdapter(it, detailsViewModel)
            })
        }
    }

}
