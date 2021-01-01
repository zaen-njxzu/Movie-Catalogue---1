package com.zaen.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zaen.moviecatalogue.R
import com.zaen.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.zaen.moviecatalogue.databinding.ContentDetailMovieBinding
import com.zaen.moviecatalogue.utils.Constants.BASE_IMAGE_URL
import com.zaen.moviecatalogue.utils.TypeMovie
import com.zaen.moviecatalogue.viewmodel.ViewModelFactory
import com.zaen.moviecatalogue.vo.Status

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var contentDetailMovieBinding: ContentDetailMovieBinding
    private lateinit var viewModel: DetailMovieViewModel

    private var menu: Menu? = null
    private var typeMovie: Int? = null

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TYPE_MOVIE = "extra_type_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        contentDetailMovieBinding = activityDetailMovieBinding.detailContent
        setContentView(activityDetailMovieBinding.root)

        setSupportActionBar(activityDetailMovieBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

        val extras = intent.extras
        if(extras != null) {
            val movieId = extras.getInt(EXTRA_MOVIE)
            typeMovie = extras.getInt(EXTRA_TYPE_MOVIE)



            viewModel.setSelectedMovie(movieId)

            when(typeMovie) {
                TypeMovie.MOVIE.ordinal -> {
                    viewModel.getMovieDetail.observe(this, Observer {
                        when(it.status) {
                            Status.LOADING -> contentDetailMovieBinding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                it.data?.apply {
                                    contentDetailMovieBinding.apply {
                                        Glide.with(this@DetailMovieActivity)
                                            .load(BASE_IMAGE_URL + posterUrl)
                                            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                                .error(R.drawable.ic_error))
                                            .into(ivPoster)
                                        tvTitle.text = title
                                        tvReleaseAt.text = releaseDate
                                        tvRating.text = rating.toString()
                                        tvSynopsis.text = overview
                                        progressBar.visibility = View.INVISIBLE
                                    }
                                }
                            }
                            Status.ERROR -> {
                                contentDetailMovieBinding.progressBar.visibility = View.INVISIBLE
                                Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                }
                TypeMovie.TV_SHOW.ordinal ->{
                    viewModel.getTvShowDetail.observe(this, Observer {
                        when(it.status) {
                            Status.LOADING -> contentDetailMovieBinding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                it.data?.apply {
                                    contentDetailMovieBinding.apply {
                                        Glide.with(this@DetailMovieActivity)
                                            .load(BASE_IMAGE_URL + posterUrl)
                                            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                                .error(R.drawable.ic_error))
                                            .into(ivPoster)
                                        tvTitle.text = title
                                        tvReleaseAt.text = releaseDate
                                        tvRating.text = rating.toString()
                                        tvSynopsis.text = overview
                                        progressBar.visibility = View.INVISIBLE
                                    }
                                }
                            }
                            Status.ERROR -> {
                                contentDetailMovieBinding.progressBar.visibility = View.INVISIBLE
                                Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                }
            }

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            when(typeMovie) {
                TypeMovie.MOVIE.ordinal -> viewModel.setFavoriteMovie()
                TypeMovie.TV_SHOW.ordinal -> viewModel.setFavoriteTvShow()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        when(typeMovie) {
            TypeMovie.MOVIE.ordinal -> {
                viewModel.getMovieDetail.observe(this, Observer {
                    it.data?.apply {
                        setFavoriteState(favorite)
                    }
                })
            }
            TypeMovie.TV_SHOW.ordinal -> {
                viewModel.getTvShowDetail.observe(this, Observer {
                    it.data?.apply {
                        setFavoriteState(favorite)
                    }
                })
            }
        }
        return true
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorited_white)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white)
        }
    }

}