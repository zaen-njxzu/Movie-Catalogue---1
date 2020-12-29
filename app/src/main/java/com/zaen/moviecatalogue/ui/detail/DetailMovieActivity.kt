package com.zaen.moviecatalogue.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.zaen.moviecatalogue.R
import com.zaen.moviecatalogue.models.MovieDetail
import com.zaen.moviecatalogue.utils.Constants.BASE_IMAGE_URL
import com.zaen.moviecatalogue.utils.TypeMovie
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.content_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TYPE_MOVIE = "extra_type_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailMovieViewModel::class.java]

        val extras = intent.extras
        if(extras != null) {
            val movieId = extras.getInt(EXTRA_MOVIE)
            val typeMovie = extras.getInt(EXTRA_TYPE_MOVIE)
            var movieDetail: MovieDetail? = null

            when(typeMovie) {
                TypeMovie.MOVIE.ordinal -> movieDetail = viewModel.getMovieDetail(movieId)
                TypeMovie.TV_SHOW.ordinal -> movieDetail = viewModel.getTvShowDetail(movieId)
            }

            movieDetail?.apply {
                Glide.with(this@DetailMovieActivity)
                    .load(posterUrl)
                    .into(iv_poster)
                tv_title.text = title
                tv_release_at.text = releaseDate
                tv_rating.text = rating.toString()
                tv_synopsis.text = overview
            }
        }
    }
}