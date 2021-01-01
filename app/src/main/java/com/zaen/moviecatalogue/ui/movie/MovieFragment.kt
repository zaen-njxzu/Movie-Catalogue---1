package com.zaen.moviecatalogue.ui.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zaen.moviecatalogue.databinding.FragmentMovieBinding
import com.zaen.moviecatalogue.pager.TypeMoviePagerAdapter.Companion.TYPE_VIEW_ARG
import com.zaen.moviecatalogue.ui.detail.DetailMovieActivity
import com.zaen.moviecatalogue.ui.detail.DetailMovieActivity.Companion.EXTRA_MOVIE
import com.zaen.moviecatalogue.ui.detail.DetailMovieActivity.Companion.EXTRA_TYPE_MOVIE
import com.zaen.moviecatalogue.utils.TypeMovie
import com.zaen.moviecatalogue.utils.TypeViewSource
import com.zaen.moviecatalogue.viewmodel.ViewModelFactory
import com.zaen.moviecatalogue.vo.Status

class MovieFragment : Fragment() {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            movieAdapter = MovieAdapter()
            movieAdapter.setOnItemClickListener {
                context?.apply {
                    val intent = Intent(this, DetailMovieActivity::class.java)
                    intent.putExtra(EXTRA_MOVIE, it)
                    intent.putExtra(EXTRA_TYPE_MOVIE, TypeMovie.MOVIE.ordinal)
                    startActivity(intent)
                }
            }

            fragmentMovieBinding.rvMovie.setHasFixedSize(true)
            fragmentMovieBinding.rvMovie.adapter = movieAdapter

            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            arguments?.takeIf { it.containsKey(TYPE_VIEW_ARG) }?.apply {
                val typeView = getInt(TYPE_VIEW_ARG)

                when(typeView) {
                    TypeViewSource.FAVORITE.ordinal -> observeMovieFavorite()
                    TypeViewSource.CATALOGUE.ordinal -> observeMovieCatalogue()
                }
            }

        }
    }

    fun observeMovieCatalogue() {
        viewModel.getMovies().observe(viewLifecycleOwner, Observer { movies ->
            if(movies != null) {
                when(movies.status) {
                    Status.LOADING -> fragmentMovieBinding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        fragmentMovieBinding.progressBar.visibility = View.INVISIBLE
                        movieAdapter.submitList(movies.data)
                    }
                    Status.ERROR -> {
                        fragmentMovieBinding.progressBar.visibility = View.INVISIBLE
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        })
    }

    fun observeMovieFavorite() {
        viewModel.getFavoriteMovies().observe(viewLifecycleOwner, Observer { movies ->
            movieAdapter.submitList(movies)
        })
    }
}