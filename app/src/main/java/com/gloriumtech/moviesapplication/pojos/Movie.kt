package com.gloriumtech.moviesapplication.pojos

import com.google.gson.annotations.SerializedName

data class Movie (
    var id: Long,
    var title: String,
    var overview: String,
    var popularity: Double,
    @SerializedName("poster_path")
    var posterPath: String,
    @SerializedName("vote_average")
    var voteAvarage: Double,
    @SerializedName("original_language")
    var originalLanguage: String,
    @SerializedName("release_date")
    var releaseDate: String)