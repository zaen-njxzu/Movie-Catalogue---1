package com.zaen.moviecatalogue.ui.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zaen.moviecatalogue.R
import com.zaen.moviecatalogue.ui.detail.DetailMovieActivity
import com.zaen.moviecatalogue.ui.detail.DetailMovieActivity.Companion.EXTRA_MOVIE
import com.zaen.moviecatalogue.ui.detail.DetailMovieActivity.Companion.EXTRA_TYPE_MOVIE
import com.zaen.moviecatalogue.utils.TypeMovie
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment(R.layout.fragment_movie) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MovieViewModel::class.java]
            val movies = viewModel.getMovies()

            val movieAdapter = MovieAdapter()
            movieAdapter.differ.submitList(movies)
            movieAdapter.setOnItemClickListener {
                context?.apply {
                    val intent = Intent(this, DetailMovieActivity::class.java)
                    intent.putExtra(EXTRA_MOVIE, it)
                    intent.putExtra(EXTRA_TYPE_MOVIE, TypeMovie.MOVIE.ordinal)
                    startActivity(intent)
                }
            }

            rv_movie.setHasFixedSize(true)
            rv_movie.adapter = movieAdapter

        }
    }

}