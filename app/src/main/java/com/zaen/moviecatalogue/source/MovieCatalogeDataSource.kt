package com.zaen.moviecatalogue.source

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.zaen.moviecatalogue.source.local.entity.MovieEntity
import com.zaen.moviecatalogue.source.local.entity.TvShowEntity
import com.zaen.moviecatalogue.source.remote.response.Movie
import com.zaen.moviecatalogue.source.remote.response.MoviesResponse
import com.zaen.moviecatalogue.vo.Resource

interface MovieCatalogeDataSource {

    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getMovie(id: Int): LiveData<Resource<MovieEntity>>

    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>

    fun setFavoriteMovie(movie: MovieEntity, isFavorite: Boolean)

    fun getTvShows(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getTvShow(id: Int): LiveData<Resource<TvShowEntity>>

    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>>

    fun setFavoriteTvShow(tvShow: TvShowEntity, isFavorite: Boolean)
}