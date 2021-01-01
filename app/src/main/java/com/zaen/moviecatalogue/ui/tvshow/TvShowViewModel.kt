package com.zaen.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.zaen.moviecatalogue.source.MovieCatalogueRepository
import com.zaen.moviecatalogue.source.local.entity.TvShowEntity
import com.zaen.moviecatalogue.vo.Resource

class TvShowViewModel(private val movieCatalogueRepository: MovieCatalogueRepository): ViewModel() {

    fun getTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> = movieCatalogueRepository.getTvShows()

    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> = movieCatalogueRepository.getFavoriteTvShows()

}