package com.zaen.moviecatalogue.source.remote

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zaen.moviecatalogue.source.remote.response.MoviesResponse
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

    fun getMovies() : LiveData<ApiResponse<MoviesResponse>> {
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<MoviesResponse>>()
        handler.postDelayed({
            resultMovies.value = ApiResponse.success(jsonHelper.loadMovies())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultMovies
    }

    fun getTvShows(): LiveData<ApiResponse<MoviesResponse>> {
        EspressoIdlingResource.increment()
        val tvShows = MutableLiveData<ApiResponse<MoviesResponse>>()
        handler.postDelayed({
            tvShows.value = ApiResponse.success(jsonHelper.loadTvShows())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return tvShows
    }

}