package com.zaen.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.zaen.moviecatalogue.source.remote.response.Movie
import com.zaen.moviecatalogue.source.MovieCatalogueRepository
import com.zaen.moviecatalogue.utils.DataDummyMovie
import com.zaen.moviecatalogue.utils.DataDummyTvShow
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    private val dummyDetailMovie = DataDummyMovie.getMovies()[0]
    private val dummyDetailTvShow = DataDummyTvShow.getTvShows()[0]
    private val movieId = dummyDetailMovie.id
    private val tvShowId = dummyDetailTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<Movie?>

    @Before
    fun setup() {
        viewModel = DetailMovieViewModel(movieCatalogueRepository)
    }

    @Test
    fun testGetMovieDetail() {
        val movie = MutableLiveData<Movie>()
        movie.value = dummyDetailMovie

        `when`(movieCatalogueRepository.getMovie(movieId)).thenReturn(movie)
        val movieEntity = viewModel.getMovieDetail(movieId).value
        verify(movieCatalogueRepository).getMovie(movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyDetailMovie.id, movieEntity?.id)
        assertEquals(dummyDetailMovie.title, movieEntity?.title)
        assertEquals(dummyDetailMovie.overview, movieEntity?.overview)
        assertEquals(dummyDetailMovie.posterUrl, movieEntity?.posterUrl)
        assertEquals(dummyDetailMovie.rating, movieEntity?.rating)
        assertEquals(dummyDetailMovie.releaseDate, movieEntity?.releaseDate)

        viewModel.getMovieDetail(movieId).observeForever(observer)
        verify(observer).onChanged(dummyDetailMovie)
    }

    @Test
    fun testGetTvShowDetail() {
        val tvShow = MutableLiveData<Movie>()
        tvShow.value = dummyDetailTvShow

        `when`(movieCatalogueRepository.getTvShow(tvShowId)).thenReturn(tvShow)
        val tvShowEntity = viewModel.getTvShowDetail(tvShowId).value
        verify(movieCatalogueRepository).getTvShow(tvShowId)
        assertNotNull(tvShowEntity)

        assertNotNull(tvShow)
        assertEquals(dummyDetailTvShow.id, tvShowEntity?.id)
        assertEquals(dummyDetailTvShow.title, tvShowEntity?.title)
        assertEquals(dummyDetailTvShow.overview, tvShowEntity?.overview)
        assertEquals(dummyDetailTvShow.posterUrl, tvShowEntity?.posterUrl)
        assertEquals(dummyDetailTvShow.rating, tvShowEntity?.rating)
        assertEquals(dummyDetailTvShow.releaseDate, tvShowEntity?.releaseDate)

        viewModel.getTvShowDetail(tvShowId).observeForever(observer)
        verify(observer).onChanged(dummyDetailTvShow)
    }
}