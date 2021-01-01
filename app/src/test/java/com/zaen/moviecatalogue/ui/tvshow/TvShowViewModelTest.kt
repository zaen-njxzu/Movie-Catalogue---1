package com.zaen.moviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.zaen.moviecatalogue.source.remote.response.MoviesResponse
import com.zaen.moviecatalogue.source.MovieCatalogueRepository
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
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<MoviesResponse>

    @Before
    fun setup() {
        viewModel = TvShowViewModel(movieCatalogueRepository)
    }

    @Test
    fun testGetTvShows() {
        val dummyTvShows = DataDummyTvShow.getTvShows()
        val tvShows = MutableLiveData<MoviesResponse>()
        tvShows.value = dummyTvShows

        `when`(movieCatalogueRepository.getTvShows()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getTvShows().value
        verify(movieCatalogueRepository).getTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities?.size)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }
}