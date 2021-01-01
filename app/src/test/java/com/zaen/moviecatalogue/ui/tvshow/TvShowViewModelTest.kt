package com.zaen.moviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.zaen.moviecatalogue.source.MovieCatalogueRepository
import com.zaen.moviecatalogue.source.local.entity.TvShowEntity
import com.zaen.moviecatalogue.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
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
    private lateinit var observer: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var observerFavorite: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setup() {
        viewModel = TvShowViewModel(movieCatalogueRepository)
    }

    @Test
    fun testGetTvShows() {
        val dummyTvShows = Resource.success(pagedList)
        `when`(dummyTvShows.data?.size).thenReturn(5)
        val tvShows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShows.value = dummyTvShows

        `when`(movieCatalogueRepository.getTvShows()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getTvShows().value?.data
        Mockito.verify(movieCatalogueRepository).getTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(5, tvShowEntities?.size)

        viewModel.getTvShows().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvShows)
    }


    @Test
    fun testGetTvShowsFavorite() {
        val dummyTvShows = pagedList
        `when`(dummyTvShows.size).thenReturn(5)
        val tvShows = MutableLiveData<PagedList<TvShowEntity>>()
        tvShows.value = dummyTvShows

        `when`(movieCatalogueRepository.getFavoriteTvShows()).thenReturn(tvShows)
        val tvShowEntities = viewModel.getFavoriteTvShows().value
        Mockito.verify(movieCatalogueRepository).getFavoriteTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(5, tvShowEntities?.size)

        viewModel.getFavoriteTvShows().observeForever(observerFavorite)
        Mockito.verify(observerFavorite).onChanged(dummyTvShows)
    }
}