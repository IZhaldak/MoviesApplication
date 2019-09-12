package com.gloriumtech.moviesapplication.pojos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieDetailed(
    @PrimaryKey
    var id: Long,
    var title: String,
    var budget: Long,
    var overview: String,
    @SerializedName("poster_path")
    var posterPath: String,
    @SerializedName("release_date")
    var releaseDate: String,
    var tagline: String,
    @SerializedName("vote_average")
    var voteAverage: Double,
    var status: String)
