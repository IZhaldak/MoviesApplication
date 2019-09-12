package com.gloriumtech.moviesapplication.network

import com.gloriumtech.moviesapplication.pojos.MovieDetailed
import com.gloriumtech.moviesapplication.pojos.MoviesPage
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {


    @GET("/3/movie/popular?api_key=$apiKey")
    suspend fun getPopularMovies(@Query("page")  page: Int = 1): MoviesPage

    @GET("/3/movie/{id}?api_key=$apiKey")
    suspend fun getMovieDetails(@Path("id") id: Long): MovieDetailed
}

const val apiKey = "b66ffea8276ce576d60df52600822c88"