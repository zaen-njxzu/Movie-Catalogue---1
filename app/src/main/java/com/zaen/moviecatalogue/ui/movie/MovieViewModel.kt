package com.zaen.moviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import com.zaen.moviecatalogue.models.Movie
import com.zaen.moviecatalogue.utils.DataDummyMovie

class MovieViewModel: ViewModel() {

    fun getMovies(): List<Movie> = DataDummyMovie.generateDummyMovies()

}