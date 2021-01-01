package com.zaen.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.zaen.moviecatalogue.source.MovieCatalogueRepository
import com.zaen.moviecatalogue.source.local.entity.MovieEntity
import com.zaen.moviecatalogue.source.local.entity.TvShowEntity
import com.zaen.moviecatalogue.utils.DataDummyMovie
import com.zaen.moviecatalogue.utils.DataDummyTvShow
import com.zaen.moviecatalogue.vo.Resource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
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
    private lateinit var observerMovie: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var observerTvShow: Observer<Resource<TvShowEntity>>

    @Before
    fun setup() {
        viewModel = DetailMovieViewModel(movieCatalogueRepository)
    }

    @Test
    fun testGetMovieDetail() {
        viewModel.setSelectedMovie(movieId)

        val dummyMovie = DataDummyMovie.getMovieDetail(movieId)?.let {
            MovieEntity(it.id, it.title, it.posterUrl, it.rating, it.overview, it.releaseDate)
        }
        val dummyMovieEntity = Resource.success(dummyMovie)
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyMovieEntity

        `when`(movieCatalogueRepository.getMovie(movieId)).thenReturn(movie)

        viewModel.getMovieDetail.observeForever(observerMovie)

        Mockito.verify(observerMovie).onChanged(dummyMovieEntity)

    }

    @Test
    fun testGetTvShowDetail() {
        viewModel.setSelectedMovie(tvShowId)

        val dummyTvShow = DataDummyTvShow.getTvShowDetail(tvShowId)?.let {
            TvShowEntity(it.id, it.title, it.posterUrl, it.rating, it.overview, it.releaseDate)
        }
        val dummyTvShowEntity = Resource.success(dummyTvShow)
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyTvShowEntity

        `when`(movieCatalogueRepository.getTvShow(tvShowId)).thenReturn(tvShow)

        viewModel.getTvShowDetail.observeForever(observerTvShow)

        Mockito.verify(observerTvShow).onChanged(dummyTvShowEntity)
    }
}