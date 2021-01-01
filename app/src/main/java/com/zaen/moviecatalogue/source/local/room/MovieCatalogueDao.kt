package com.zaen.moviecatalogue.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.zaen.moviecatalogue.source.local.entity.MovieEntity
import com.zaen.moviecatalogue.source.local.entity.TvShowEntity

@Dao
interface MovieCatalogueDao {

    @Query("SELECT * FROM movieentities ORDER by id")
    fun getMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieentities WHERE id = :id")
    fun getMovie(id: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM movieentities WHERE favorite = 1 ORDER by id")
    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun update(movie: MovieEntity)

    @Query("SELECT * FROM tvshowentities ORDER by id")
    fun getTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tvshowentities WHERE id = :id")
    fun getTvShow(id: Int): LiveData<TvShowEntity>

    @Query("SELECT * FROM tvshowentities WHERE favorite = 1 ORDER by id")
    fun getFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShowEntity>)

    @Update
    fun update(tvShow: TvShowEntity)

}