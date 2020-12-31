package com.zaen.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zaen.moviecatalogue.models.Movie
import com.zaen.moviecatalogue.source.MovieCatalogueRepository

class DetailMovieViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {

    fun getMovieDetail(id: Int) : LiveData<Movie?> = movieCatalogueRepository.getMovie(id)

    fun getTvShowDetail(id: Int) : LiveData<Movie?> = movieCatalogueRepository.getTvShow(id)

}