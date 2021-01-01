package com.zaen.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zaen.moviecatalogue.R
import com.zaen.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.zaen.moviecatalogue.databinding.ContentDetailMovieBinding
import com.zaen.moviecatalogue.models.Movie
import com.zaen.moviecatalogue.utils.Constants.BASE_IMAGE_URL
import com.zaen.moviecatalogue.utils.TypeMovie
import com.zaen.moviecatalogue.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.content_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var contentDetailMovieBinding: ContentDetailMovieBinding

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TYPE_MOVIE = "extra_type_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        contentDetailMovieBinding = activityDetailMovieBinding.detailContent
        setContentView(activityDetailMovieBinding.root)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

        val extras = intent.extras
        if(extras != null) {
            val movieId = extras.getInt(EXTRA_MOVIE)
            val typeMovie = extras.getInt(EXTRA_TYPE_MOVIE)

            contentDetailMovieBinding.progressBar.visibility = View.VISIBLE

            when(typeMovie) {
                TypeMovie.MOVIE.ordinal -> {
                    viewModel.getMovieDetail(movieId).observe(this, Observer {
                        it?.let {
                            updateUi(it)
                        }
                    })
                }
                TypeMovie.TV_SHOW.ordinal ->{
                    viewModel.getTvShowDetail(movieId).observe(this, Observer {
                        it?.let {
                            updateUi(it)
                        }
                    })
                }
            }

        }
    }

    private fun updateUi(movie: Movie) {
        movie.apply {
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
}