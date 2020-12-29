package com.zaen.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import com.zaen.moviecatalogue.models.MovieDetail
import com.zaen.moviecatalogue.utils.DataDummyMovie
import com.zaen.moviecatalogue.utils.DataDummyTvShow

class DetailMovieViewModel : ViewModel() {

    fun getMovieDetail(id: Int) : MovieDetail? = DataDummyMovie.getMovieDetails(id)

    fun getTvShowDetail(id: Int) : MovieDetail? = DataDummyTvShow.getTvShowDetails(id)

}