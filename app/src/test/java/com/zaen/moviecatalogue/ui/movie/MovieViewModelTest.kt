package com.zaen.moviecatalogue.ui.movie


import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setup() {
        viewModel = MovieViewModel()
    }

    @Test
    fun testGetMovies() {
        val movies = viewModel.getMovies()
        assertNotNull(movies)
        assertEquals(10, movies.size)
    }
}