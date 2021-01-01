package com.zaen.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.zaen.moviecatalogue.source.MovieCatalogueRepository
import com.zaen.moviecatalogue.source.local.entity.MovieEntity
import com.zaen.moviecatalogue.source.local.entity.TvShowEntity
import com.zaen.moviecatalogue.utils.DataDummyMovie
import com.zaen.moviecatalogue.utils.DataDummyTvShow
import com.zaen.moviecatalogue.utils.LiveDataTestUtil
import com.zaen.moviecatalogue.vo.Resource
import org.junit.Assert
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

        `when`<LiveData<Resource<MovieEntity>>>(movieCatalogueRepository.getMovie(movieId)).thenReturn(movie)
        val movieEntity = LiveDataTestUtil.getValue(viewModel.getMovieDetail)
        Mockito.verify(movieCatalogueRepository).getMovie(movieId)

        Assert.assertNotNull(movieEntity)
        Assert.assertNotNull(movieEntity.data?.id)
        Assert.assertNotNull(movieEntity.data?.title)
        Assert.assertNotNull(movieEntity.data?.posterUrl)
        Assert.assertNotNull(movieEntity.data?.rating)
        Assert.assertNotNull(movieEntity.data?.overview)
        Assert.assertNotNull(movieEntity.data?.releaseDate)


        Assert.assertEquals(dummyMovie?.title, movieEntity.data?.title)
        Assert.assertEquals(dummyMovie?.posterUrl, movieEntity.data?.posterUrl)
        Assert.assertEquals(dummyMovie?.id, movieEntity.data?.id)
        Assert.assertEquals(dummyMovie?.overview, movieEntity.data?.overview)
        Assert.assertEquals(dummyMovie?.rating, movieEntity.data?.rating)
        Assert.assertEquals(dummyMovie?.releaseDate, movieEntity.data?.releaseDate)

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

        `when`<LiveData<Resource<TvShowEntity>>>(movieCatalogueRepository.getTvShow(tvShowId)).thenReturn(tvShow)
        val tvShowEntity = LiveDataTestUtil.getValue(viewModel.getTvShowDetail)
        Mockito.verify(movieCatalogueRepository).getTvShow(tvShowId)

        Assert.assertNotNull(tvShowEntity)
        Assert.assertNotNull(tvShowEntity.data?.id)
        Assert.assertNotNull(tvShowEntity.data?.title)
        Assert.assertNotNull(tvShowEntity.data?.posterUrl)
        Assert.assertNotNull(tvShowEntity.data?.rating)
        Assert.assertNotNull(tvShowEntity.data?.overview)
        Assert.assertNotNull(tvShowEntity.data?.releaseDate)

        Assert.assertEquals(dummyTvShow?.title, tvShowEntity.data?.title)
        Assert.assertEquals(dummyTvShow?.posterUrl, tvShowEntity.data?.posterUrl)
        Assert.assertEquals(dummyTvShow?.id, tvShowEntity.data?.id)
        Assert.assertEquals(dummyTvShow?.overview, tvShowEntity.data?.overview)
        Assert.assertEquals(dummyTvShow?.rating, tvShowEntity.data?.rating)
        Assert.assertEquals(dummyTvShow?.releaseDate, tvShowEntity.data?.releaseDate)

        viewModel.getTvShowDetail.observeForever(observerTvShow)
        Mockito.verify(observerTvShow).onChanged(dummyTvShowEntity)
    }
}