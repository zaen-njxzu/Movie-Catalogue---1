package com.zaen.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.zaen.moviecatalogue.source.MovieCatalogueRepository
import com.zaen.moviecatalogue.source.local.entity.MovieEntity
import com.zaen.moviecatalogue.vo.Resource

class MovieViewModel(private val movieCatalogueRepository: MovieCatalogueRepository): ViewModel() {

    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> = movieCatalogueRepository.getMovies()

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> = movieCatalogueRepository.getFavoriteMovies()

}