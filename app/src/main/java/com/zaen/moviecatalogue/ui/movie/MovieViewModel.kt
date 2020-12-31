package com.zaen.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zaen.moviecatalogue.models.MoviesResponse
import com.zaen.moviecatalogue.source.MovieCatalogueRepository

class MovieViewModel(private val movieCatalogueRepository: MovieCatalogueRepository): ViewModel() {

    fun getMovies(): LiveData<MoviesResponse> = movieCatalogueRepository.getMovies()

}