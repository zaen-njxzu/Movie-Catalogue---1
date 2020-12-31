package com.zaen.moviecatalogue.source

import androidx.lifecycle.LiveData
import com.zaen.moviecatalogue.models.Movie
import com.zaen.moviecatalogue.models.MoviesResponse

interface MovieCatalogeDataSource {

    fun getMovies(): LiveData<MoviesResponse>

    fun getMovie(id: Int): LiveData<Movie?>

    fun getTvShows(): LiveData<MoviesResponse>

    fun getTvShow(id: Int): LiveData<Movie?>
}