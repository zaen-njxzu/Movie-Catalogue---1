package com.zaen.moviecatalogue.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zaen.moviecatalogue.R
import com.zaen.moviecatalogue.databinding.FragmentMovieBinding
import com.zaen.moviecatalogue.ui.detail.DetailMovieActivity
import com.zaen.moviecatalogue.ui.detail.DetailMovieActivity.Companion.EXTRA_MOVIE
import com.zaen.moviecatalogue.ui.detail.DetailMovieActivity.Companion.EXTRA_TYPE_MOVIE
import com.zaen.moviecatalogue.utils.TypeMovie
import com.zaen.moviecatalogue.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {

    private lateinit var fragmentMovieBinding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val movieAdapter = MovieAdapter()
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
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            fragmentMovieBinding.progressBar.visibility = View.VISIBLE
            viewModel.getMovies().observe(viewLifecycleOwner, Observer {
                movieAdapter.differ.submitList(it)
                fragmentMovieBinding.progressBar.visibility = View.INVISIBLE
            })
        }
    }

}