package com.zaen.moviecatalogue.source.remote

import android.os.Handler
import com.zaen.moviecatalogue.models.Movie
import com.zaen.moviecatalogue.models.MoviesResponse
import com.zaen.moviecatalogue.utils.EspressoIdlingResource
import com.zaen.moviecatalogue.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper){
    private val handler = Handler()

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper)
            }
    }

    fun getMovies(callback: LoadMovies) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onMoviesReceived(jsonHelper.loadMovies())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getMovie(id: Int, callback: LoadMovieDetail) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onMovieDetailReceived(jsonHelper.loadMovieById(id))
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getTvShows(callback: LoadTvShows) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onTvShowsReceived(jsonHelper.loadTvShows())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getTvShow(id: Int, callback: LoadTvShowDetail) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onTvShowDetailReceived(jsonHelper.loadTvShowById(id))
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadMovies {
        fun onMoviesReceived(moviesResponse: MoviesResponse)
    }

    interface LoadMovieDetail {
        fun onMovieDetailReceived(movie: Movie?)
    }

    interface LoadTvShows {
        fun onTvShowsReceived(tvShowsResponse: MoviesResponse)
    }

    interface LoadTvShowDetail {
        fun onTvShowDetailReceived(tvShow: Movie?)
    }
}