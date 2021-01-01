package com.zaen.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.zaen.moviecatalogue.source.MovieCatalogueRepository
import com.zaen.moviecatalogue.source.local.entity.MovieEntity
import com.zaen.moviecatalogue.source.local.entity.TvShowEntity
import com.zaen.moviecatalogue.vo.Resource

class DetailMovieViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {

    val movieId = MutableLiveData<Int>()

    fun setSelectedMovie(id: Int) {
        this.movieId.value = id
    }

    var getMovieDetail: LiveData<Resource<MovieEntity>> = Transformations.switchMap(movieId) { movieId ->
        movieCatalogueRepository.getMovie(movieId)
    }

    var getTvShowDetail : LiveData<Resource<TvShowEntity>> = Transformations.switchMap(movieId) { movieId ->
        movieCatalogueRepository.getTvShow(movieId)
    }

    fun setFavoriteMovie() {
        val movie = getMovieDetail.value
        movie?.data?.apply {
            val newState = !favorite
            movieCatalogueRepository.setFavoriteMovie(this, newState)
        }
    }

    fun setFavoriteTvShow() {
        val tvShow = getTvShowDetail.value
        tvShow?.data?.apply {
            val newState = !favorite
            movieCatalogueRepository.setFavoriteTvShow(this, newState)
        }
    }
}