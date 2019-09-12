package com.gloriumtech.moviesapplication.pojos

data class MoviesPage (
    var page: Int,
    var results: List<Movie>
)