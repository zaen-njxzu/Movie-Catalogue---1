package com.zaen.moviecatalogue.ui.tvshow

import androidx.lifecycle.ViewModel
import com.zaen.moviecatalogue.models.Movie
import com.zaen.moviecatalogue.utils.DataDummyTvShow

class TvShowViewModel: ViewModel() {

    fun getTvShows(): List<Movie> = DataDummyTvShow.getTvShows()

}