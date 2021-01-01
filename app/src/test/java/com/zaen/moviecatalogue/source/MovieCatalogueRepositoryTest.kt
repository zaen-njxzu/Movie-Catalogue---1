package com.zaen.moviecatalogue.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.verify
import com.zaen.moviecatalogue.source.local.LocalDataSource
import com.zaen.moviecatalogue.source.local.entity.MovieEntity
import com.zaen.moviecatalogue.source.local.entity.TvShowEntity
import com.zaen.moviecatalogue.source.remote.RemoteDataSource
import com.zaen.moviecatalogue.utils.*
import com.zaen.moviecatalogue.vo.Resource
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Rule
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class MovieCatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)
    private val movieCatalogueRepository = FakeMovieCatalogueRepository(remote, local, appExecutors)

    private val moviesResponse = DataDummyMovie.getMovies()
    private val movieId = moviesResponse[0].id
    private val tvShowsResponse = DataDummyTvShow.getTvShows()
    private val tvShowId = tvShowsResponse[0].id
    private val movieDetail = DataDummyMovie.getMovieDetail(movieId)
    private val tvShowDetail = DataDummyTvShow.getTvShowDetail(tvShowId)

    @Test
    fun getMovies() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getMovies()).thenReturn(dataSourceFactory)
        movieCatalogueRepository.getMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummyMovie.getMovies()))
        verify(local).getMovies()
        assertNotNull(movieEntities.data)
        assertEquals(moviesResponse.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getMovie() {
        val dummyEntity = MutableLiveData<MovieEntity>()
        val movie = DataDummyMovie.getMovieDetail(movieId)
        assertNotNull(movie)

        val dummyMovieEntity = movie?.let {
            MovieEntity(it.id, it.title, it.posterUrl, it.rating, it.overview, it.releaseDate)
        }
        dummyEntity.value = dummyMovieEntity

        `when`<LiveData<MovieEntity>>(local.getMovie(movieId)).thenReturn(dummyEntity)

        val movieEntity = LiveDataTestUtil.getValue(movieCatalogueRepository.getMovie(movieId))
        verify(local).getMovie(movieId)
        assertNotNull(movieEntity)
        assertNotNull(movieEntity.data?.title)
        assertNotNull(movieEntity.data?.posterUrl)
        assertNotNull(movieEntity.data?.id)
        assertNotNull(movieEntity.data?.overview)
        assertNotNull(movieEntity.data?.rating)
        assertNotNull(movieEntity.data?.releaseDate)

        assertEquals(movieDetail?.title, movieEntity.data?.title)
        assertEquals(movieDetail?.posterUrl, movieEntity.data?.posterUrl)
        assertEquals(movieDetail?.id, movieEntity.data?.id)
        assertEquals(movieDetail?.overview, movieEntity.data?.overview)
        assertEquals(movieDetail?.rating, movieEntity.data?.rating)
        assertEquals(movieDetail?.releaseDate, movieEntity.data?.releaseDate)
    }

    @Test
    fun getTvShows() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getTvShows()).thenReturn(dataSourceFactory)
        movieCatalogueRepository.getTvShows()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummyTvShow.getTvShows()))
        verify(local).getTvShows()
        assertNotNull(tvShowEntities.data)
        assertEquals(moviesResponse.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getTvShow() {
        val dummyEntity = MutableLiveData<TvShowEntity>()
        val tvShow = DataDummyTvShow.getTvShowDetail(tvShowId)
        assertNotNull(tvShow)

        val dummyTvShowEntity = tvShow?.let {
            TvShowEntity(it.id, it.title, it.posterUrl, it.rating, it.overview, it.releaseDate)
        }
        dummyEntity.value = dummyTvShowEntity

        `when`<LiveData<TvShowEntity>>(local.getTvShow(tvShowId)).thenReturn(dummyEntity)

        val tvShowEntity = LiveDataTestUtil.getValue(movieCatalogueRepository.getTvShow(tvShowId))
        verify(local).getTvShow(tvShowId)
        assertNotNull(tvShowEntity)
        assertNotNull(tvShowEntity.data?.title)
        assertNotNull(tvShowEntity.data?.posterUrl)
        assertNotNull(tvShowEntity.data?.id)
        assertNotNull(tvShowEntity.data?.overview)
        assertNotNull(tvShowEntity.data?.rating)
        assertNotNull(tvShowEntity.data?.releaseDate)

        assertEquals(tvShowDetail?.title, tvShowEntity.data?.title)
        assertEquals(tvShowDetail?.posterUrl, tvShowEntity.data?.posterUrl)
        assertEquals(tvShowDetail?.id, tvShowEntity.data?.id)
        assertEquals(tvShowDetail?.overview, tvShowEntity.data?.overview)
        assertEquals(tvShowDetail?.rating, tvShowEntity.data?.rating)
        assertEquals(tvShowDetail?.releaseDate, tvShowEntity.data?.releaseDate)
    }
}