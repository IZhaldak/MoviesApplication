package com.gloriumtech.moviesapplication.pojos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Genre(
    @PrimaryKey
    var id: Long,
    var name: String
)