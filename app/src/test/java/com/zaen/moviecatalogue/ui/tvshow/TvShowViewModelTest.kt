package com.zaen.moviecatalogue.ui.tvshow

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class TvShowViewModelTest {


    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setup() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun testGetTvShows() {
        val tvShows = viewModel.getTvShows()
        assertNotNull(tvShows)
        assertEquals(11, tvShows.size)
    }
}