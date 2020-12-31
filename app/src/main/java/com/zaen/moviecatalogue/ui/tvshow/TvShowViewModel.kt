package com.zaen.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zaen.moviecatalogue.models.MoviesResponse
import com.zaen.moviecatalogue.source.MovieCatalogueRepository

class TvShowViewModel(private val movieCatalogueRepository: MovieCatalogueRepository): ViewModel() {

    fun getTvShows(): LiveData<MoviesResponse> = movieCatalogueRepository.getTvShows()

}