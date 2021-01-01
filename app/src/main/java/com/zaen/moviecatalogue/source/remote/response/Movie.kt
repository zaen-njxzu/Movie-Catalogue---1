package com.zaen.moviecatalogue.source.remote.response

data class Movie(
    val id: Int,
    val title: String,
    val posterUrl: String,
    val rating: Double,
    val overview: String,
    val releaseDate: String
)