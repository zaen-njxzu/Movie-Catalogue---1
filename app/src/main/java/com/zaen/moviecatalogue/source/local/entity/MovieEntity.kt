package com.zaen.moviecatalogue.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieentities")
data class MovieEntity (
    @NonNull
    @PrimaryKey
    var id: Int,
    var title: String,
    var posterUrl: String,
    var rating: Double,
    var overview: String,
    var releaseDate: String,
    var favorite: Boolean = false
)