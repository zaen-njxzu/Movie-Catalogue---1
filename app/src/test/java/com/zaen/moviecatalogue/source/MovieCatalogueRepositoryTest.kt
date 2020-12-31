package com.zaen.moviecatalogue.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.academies.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.zaen.moviecatalogue.source.remote.RemoteDataSource
import com.zaen.moviecatalogue.utils.DataDummyMovie
import com.zaen.moviecatalogue.utils.DataDummyTvShow
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Rule
import org.mockito.Mockito

class MovieCatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val movieCatalogueRepository = FakeMovieCatalogueRepository(remote)

    private val moviesResponse = DataDummyMovie.getMovies()
    private val movieId = moviesResponse[0].id
    private val tvShowsResponse = DataDummyTvShow.getTvShows()
    private val tvShowId = tvShowsResponse[0].id
    private val movieDetail = DataDummyMovie.getMovieDetail(movieId)
    private val tvShowDetail = DataDummyTvShow.getTvShowDetail(tvShowId)

    @Test
    fun getMovies() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[0] as RemoteDataSource.LoadMovies)
                    .onMoviesReceived(moviesResponse)
            null
        }.`when`(remote).getMovies(any())

        val movieEntities = LiveDataTestUtil.getValue(movieCatalogueRepository.getMovies())

        verify(remote).getMovies(any())
        assertNotNull(movieEntities)
        assertEquals(moviesResponse.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getMovie() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[1] as RemoteDataSource.LoadMovieDetail)
                    .onMovieDetailReceived(movieDetail)
            null
        }.`when`(remote).getMovie(eq(movieId),any())

        val movieEntity = LiveDataTestUtil.getValue(movieCatalogueRepository.getMovie(movieId))

        verify(remote).getMovie(eq(movieId),any())

        assertNotNull(movieEntity)
        assertNotNull(movieEntity?.title)
        assertNotNull(movieEntity?.posterUrl)
        assertNotNull(movieEntity?.id)
        assertNotNull(movieEntity?.overview)
        assertNotNull(movieEntity?.rating)
        assertNotNull(movieEntity?.releaseDate)

        assertEquals(movieDetail?.title, movieEntity?.title)
        assertEquals(movieDetail?.posterUrl, movieEntity?.posterUrl)
        assertEquals(movieDetail?.id, movieEntity?.id)
        assertEquals(movieDetail?.overview, movieEntity?.overview)
        assertEquals(movieDetail?.rating, movieEntity?.rating)
        assertEquals(movieDetail?.releaseDate, movieEntity?.releaseDate)
    }

    @Test
    fun getTvShows() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[0] as RemoteDataSource.LoadTvShows)
                    .onTvShowsReceived(tvShowsResponse)
            null
        }.`when`(remote).getTvShows(any())

        val tvShowEntities = LiveDataTestUtil.getValue(movieCatalogueRepository.getTvShows())

        verify(remote).getTvShows(any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShowsResponse.size.toLong(), tvShowEntities.size.toLong())
    }

    @Test
    fun getTvShow() {
        doAnswer { invocationOnMock ->
            (invocationOnMock.arguments[1] as RemoteDataSource.LoadTvShowDetail)
                    .onTvShowDetailReceived(tvShowDetail)
            null
        }.`when`(remote).getTvShow(eq(tvShowId),any())

        val tvShowEntity = LiveDataTestUtil.getValue(movieCatalogueRepository.getTvShow(tvShowId))

        verify(remote).getTvShow(eq(tvShowId),any())

        assertNotNull(tvShowEntity)
        assertNotNull(tvShowEntity?.title)
        assertNotNull(tvShowEntity?.posterUrl)
        assertNotNull(tvShowEntity?.id)
        assertNotNull(tvShowEntity?.overview)
        assertNotNull(tvShowEntity?.rating)
        assertNotNull(tvShowEntity?.releaseDate)

        assertEquals(tvShowDetail?.title, tvShowEntity?.title)
        assertEquals(tvShowDetail?.posterUrl, tvShowEntity?.posterUrl)
        assertEquals(tvShowDetail?.id, tvShowEntity?.id)
        assertEquals(tvShowDetail?.overview, tvShowEntity?.overview)
        assertEquals(tvShowDetail?.rating, tvShowEntity?.rating)
        assertEquals(tvShowDetail?.releaseDate, tvShowEntity?.releaseDate)
    }
}