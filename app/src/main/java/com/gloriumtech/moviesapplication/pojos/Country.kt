package com.gloriumtech.moviesapplication.pojos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Country(
    @PrimaryKey
    var iso_3166_1: String,
    var name: String
)