package com.zaen.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import com.zaen.moviecatalogue.models.Movie
import com.zaen.moviecatalogue.utils.DataDummyMovie
import com.zaen.moviecatalogue.utils.DataDummyTvShow

class DetailMovieViewModel : ViewModel() {

    fun getMovieDetail(id: Int) : Movie? = DataDummyMovie.getMovieDetail(id)

    fun getTvShowDetail(id: Int) : Movie? = DataDummyTvShow.getTvShowDetail(id)

}