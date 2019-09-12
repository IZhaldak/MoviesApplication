package com.gloriumtech.moviesapplication.ui.movies

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.gloriumtech.moviesapplication.MainActivity

import com.gloriumtech.moviesapplication.R
import com.gloriumtech.moviesapplication.pojos.Movie
import com.gloriumtech.moviesapplication.ui.movie_details.MovieDetailsViewModel
import kotlinx.android.synthetic.main.movies_fragment.*

class MoviesFragment : Fragment() {

    companion object {
        fun newInstance() = MoviesFragment()
    }

    private lateinit var viewModel: MoviesViewModel
    private lateinit var adapter : MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)

        val mainActivity = activity as MainActivity
        val detailsViewModel = ViewModelProviders.of(mainActivity).get(MovieDetailsViewModel::class.java)

        adapter = MoviesAdapter(detailsViewModel)
        recycler.adapter =  adapter
        recycler.layoutManager = LinearLayoutManager(context)

        button_saved.setOnClickListener {
            val navController = it.findNavController()
            navController.navigate(R.id.savedMoviesFragment)
        }

        viewModel.moviesLiveData.observe(this, Observer<PagedList<Movie>> {
            progress.visibility = View.GONE
            adapter.submitList(it)
        })
    }

}
