package com.zaen.moviecatalogue.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.zaen.moviecatalogue.source.local.entity.MovieEntity
import com.zaen.moviecatalogue.source.local.entity.TvShowEntity
import com.zaen.moviecatalogue.source.local.room.MovieCatalogueDao

class LocalDataSource private constructor(private val mMovieCatalogueDao: MovieCatalogueDao){

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(mMovieCatalogueDao: MovieCatalogueDao): LocalDataSource {
            if (INSTANCE == null) {
                INSTANCE = LocalDataSource(mMovieCatalogueDao)
            }
            return INSTANCE as LocalDataSource
        }
    }

    fun getMovies(): DataSource.Factory<Int, MovieEntity> = mMovieCatalogueDao.getMovies()

    fun getMovie(id: Int): LiveData<MovieEntity> = mMovieCatalogueDao.getMovie(id)

    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> = mMovieCatalogueDao.getFavoriteMovies()

    fun insertMovies(movies: List<MovieEntity>) = mMovieCatalogueDao.insertMovies(movies)

    fun setFavoriteMovie(movie: MovieEntity, isFavorite: Boolean) {
        movie.favorite = isFavorite
        mMovieCatalogueDao.update(movie)
    }

    fun getTvShows(): DataSource.Factory<Int, TvShowEntity> = mMovieCatalogueDao.getTvShows()

    fun getTvShow(id: Int): LiveData<TvShowEntity> = mMovieCatalogueDao.getTvShow(id)

    fun getFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity> = mMovieCatalogueDao.getFavoriteTvShows()

    fun insertTvShows(tvShows: List<TvShowEntity>) = mMovieCatalogueDao.insertTvShows(tvShows)

    fun setFavoriteTvShow(tvShow: TvShowEntity, isFavorite: Boolean) {
        tvShow.favorite = isFavorite
        mMovieCatalogueDao.update(tvShow)
    }
}