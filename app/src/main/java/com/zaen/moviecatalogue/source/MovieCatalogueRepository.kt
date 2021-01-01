package com.zaen.moviecatalogue.source

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.zaen.moviecatalogue.source.local.LocalDataSource
import com.zaen.moviecatalogue.source.local.entity.MovieEntity
import com.zaen.moviecatalogue.source.local.entity.TvShowEntity
import com.zaen.moviecatalogue.source.remote.ApiResponse
import com.zaen.moviecatalogue.source.remote.response.Movie
import com.zaen.moviecatalogue.source.remote.response.MoviesResponse
import com.zaen.moviecatalogue.source.remote.RemoteDataSource
import com.zaen.moviecatalogue.utils.AppExecutors
import com.zaen.moviecatalogue.vo.Resource

class MovieCatalogueRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieCatalogeDataSource {

    companion object {
        @Volatile
        private var instance: MovieCatalogueRepository? = null

        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): MovieCatalogueRepository =
            instance ?: synchronized(this) {
                instance ?: MovieCatalogueRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, MoviesResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(2)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<MoviesResponse>> =
                remoteDataSource.getMovies()

            override fun saveCallResult(data: MoviesResponse) {
                val movieList = ArrayList<MovieEntity>()
                for(response in data) {
                    response.apply {
                        val movieEntity = MovieEntity(
                            id,
                            title,
                            posterUrl,
                            rating,
                            overview,
                            releaseDate
                        )
                        movieList.add(movieEntity)
                    }
                }

                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getMovie(id: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MoviesResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> = localDataSource.getMovie(id)

            override fun shouldFetch(data: MovieEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<MoviesResponse>> = remoteDataSource.getMovies()

            override fun saveCallResult(data: MoviesResponse) {
                val movieList = ArrayList<MovieEntity>()
                for(response in data) {
                    response.apply {
                        val movieEntity = MovieEntity(
                            id,
                            title,
                            posterUrl,
                            rating,
                            overview,
                            releaseDate
                        )
                        movieList.add(movieEntity)
                    }
                }

                localDataSource.insertMovies(movieList)
            }

        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(2)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun setFavoriteMovie(movie: MovieEntity, isFavorite: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movie, isFavorite) }

    override fun getTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, MoviesResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(2)
                    .build()
                return LivePagedListBuilder(localDataSource.getTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<MoviesResponse>> =
                remoteDataSource.getTvShows()

            override fun saveCallResult(data: MoviesResponse) {
                val tvShows = ArrayList<TvShowEntity>()
                for(response in data) {
                    response.apply {
                        val tvShowEntity = TvShowEntity(
                            id,
                            title,
                            posterUrl,
                            rating,
                            overview,
                            releaseDate
                        )
                        tvShows.add(tvShowEntity)
                    }
                }

                localDataSource.insertTvShows(tvShows)
            }
        }.asLiveData()
    }

    override fun getTvShow(id: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, MoviesResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> = localDataSource.getTvShow(id)

            override fun shouldFetch(data: TvShowEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<MoviesResponse>> = remoteDataSource.getTvShows()

            override fun saveCallResult(data: MoviesResponse) {
                val tvShows = ArrayList<TvShowEntity>()
                for(response in data) {
                    response.apply {
                        val tvShowEntity = TvShowEntity(
                            id,
                            title,
                            posterUrl,
                            rating,
                            overview,
                            releaseDate
                        )
                        tvShows.add(tvShowEntity)
                    }
                }

                localDataSource.insertTvShows(tvShows)
            }
        }.asLiveData()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(2)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShows(), config).build()
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, isFavorite: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setFavoriteTvShow(tvShow, isFavorite) }

}