package com.zaen.moviecatalogue.ui.detail

import com.zaen.moviecatalogue.utils.DataDummyMovie
import com.zaen.moviecatalogue.utils.DataDummyTvShow
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    private val dummyDetailMovie = DataDummyMovie.getMoviesDetail()[0]
    private val dummyDetailTvShow = DataDummyTvShow.getTvShowsDetail()[0]
    private val movieId = dummyDetailMovie.id
    private val tvShowId = dummyDetailTvShow.id

    @Before
    fun setup() {
        viewModel = DetailMovieViewModel()
    }

    @Test
    fun testGetMovieDetail() {
        val movie = viewModel.getMovieDetail(movieId)
        assertNotNull(movie)
        assertEquals(dummyDetailMovie.id, movie?.id)
        assertEquals(dummyDetailMovie.title, movie?.title)
        assertEquals(dummyDetailMovie.overview, movie?.overview)
        assertEquals(dummyDetailMovie.posterUrl, movie?.posterUrl)
        assertEquals(dummyDetailMovie.rating, movie?.rating)
        assertEquals(dummyDetailMovie.releaseDate, movie?.releaseDate)
    }

    @Test
    fun testGetTvShowDetail() {
        val tvShow = viewModel.getTvShowDetail(tvShowId)
        assertNotNull(tvShow)
        assertEquals(dummyDetailTvShow.id, tvShow?.id)
        assertEquals(dummyDetailTvShow.title, tvShow?.title)
        assertEquals(dummyDetailTvShow.overview, tvShow?.overview)
        assertEquals(dummyDetailTvShow.posterUrl, tvShow?.posterUrl)
        assertEquals(dummyDetailTvShow.rating, tvShow?.rating)
        assertEquals(dummyDetailTvShow.releaseDate, tvShow?.releaseDate)
    }
}