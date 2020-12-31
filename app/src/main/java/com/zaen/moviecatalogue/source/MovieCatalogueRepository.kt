package com.zaen.moviecatalogue.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zaen.moviecatalogue.models.Movie
import com.zaen.moviecatalogue.models.MoviesResponse
import com.zaen.moviecatalogue.source.remote.RemoteDataSource

class MovieCatalogueRepository private constructor(private val remoteDataSource: RemoteDataSource) : MovieCatalogeDataSource {

    companion object {
        @Volatile
        private var instance: MovieCatalogueRepository? = null

        fun getInstance(remoteData: RemoteDataSource): MovieCatalogueRepository =
            instance ?: synchronized(this) {
                instance ?: MovieCatalogueRepository(remoteData)
            }
    }

    override fun getMovies(): LiveData<MoviesResponse> {
        val movies = MutableLiveData<MoviesResponse>()
        remoteDataSource.getMovies(object : RemoteDataSource.LoadMovies {
            override fun onMoviesReceived(moviesResponse: MoviesResponse) {
                movies.postValue(moviesResponse)
            }
        })
        return movies
    }

    override fun getMovie(id: Int): LiveData<Movie?> {
        val detailMovie = MutableLiveData<Movie>()
        remoteDataSource.getMovie(id, object : RemoteDataSource.LoadMovieDetail {
            override fun onMovieDetailReceived(movie: Movie?) {
                detailMovie.postValue(movie)
            }
        })
        return detailMovie
    }

    override fun getTvShows(): LiveData<MoviesResponse> {
        val tvShows = MutableLiveData<MoviesResponse>()
        remoteDataSource.getTvShows(object : RemoteDataSource.LoadTvShows {
            override fun onTvShowsReceived(tvShowsResponse: MoviesResponse) {
                tvShows.postValue(tvShowsResponse)
            }
        })
        return tvShows
    }

    override fun getTvShow(id: Int): LiveData<Movie?> {
        val detailTvShow = MutableLiveData<Movie>()
        remoteDataSource.getTvShow(id, object : RemoteDataSource.LoadTvShowDetail {
            override fun onTvShowDetailReceived(tvShow: Movie?) {
                detailTvShow.postValue(tvShow)
            }
        })
        return detailTvShow
    }
}