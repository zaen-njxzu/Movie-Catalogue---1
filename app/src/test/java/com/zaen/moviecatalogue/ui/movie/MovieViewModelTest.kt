package com.zaen.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.zaen.moviecatalogue.source.MovieCatalogueRepository
import com.zaen.moviecatalogue.source.local.entity.MovieEntity
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
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var observerFavorite: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setup() {
        viewModel = MovieViewModel(movieCatalogueRepository)
    }

    @Test
    fun testGetMovies() {
        val dummyMovies = Resource.success(pagedList)
        `when`(dummyMovies.data?.size).thenReturn(5)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovies

        `when`(movieCatalogueRepository.getMovies()).thenReturn(movies)
        val movieEntities = viewModel.getMovies().value?.data
        Mockito.verify(movieCatalogueRepository).getMovies()
        assertNotNull(movieEntities)
        assertEquals(5, movieEntities?.size)

        viewModel.getMovies().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyMovies)
    }


    @Test
    fun testGetMoviesFavorite() {
        val dummyMovies = pagedList
        `when`(dummyMovies.size).thenReturn(5)
        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyMovies

        `when`(movieCatalogueRepository.getFavoriteMovies()).thenReturn(movies)
        val movieEntities = viewModel.getFavoriteMovies().value
        Mockito.verify(movieCatalogueRepository).getFavoriteMovies()
        assertNotNull(movieEntities)
        assertEquals(5, movieEntities?.size)

        viewModel.getFavoriteMovies().observeForever(observerFavorite)
        Mockito.verify(observerFavorite).onChanged(dummyMovies)
    }
}