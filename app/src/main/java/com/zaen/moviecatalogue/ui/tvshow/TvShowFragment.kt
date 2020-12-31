package com.zaen.moviecatalogue.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zaen.moviecatalogue.R
import com.zaen.moviecatalogue.databinding.FragmentTvShowBinding
import com.zaen.moviecatalogue.ui.detail.DetailMovieActivity
import com.zaen.moviecatalogue.utils.TypeMovie
import com.zaen.moviecatalogue.viewmodel.ViewModelFactory

class TvShowFragment : Fragment(R.layout.fragment_tv_show) {

    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(inflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val tvShowAdapter = TvShowAdapter()
            tvShowAdapter.setOnItemClickListener {
                context?.apply {
                    val intent = Intent(this, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, it)
                    intent.putExtra(DetailMovieActivity.EXTRA_TYPE_MOVIE, TypeMovie.TV_SHOW.ordinal)
                    startActivity(intent)
                }
            }
            tvShowAdapter.setOnClickShareListener { movie ->
                if(activity != null) {
                    val mimeType = "text/plain"
                    ShareCompat.IntentBuilder
                        .from(activity!!)
                        .setType(mimeType)
                        .setChooserTitle("Bagikan aplikasi ini sekarang.")
                        .setText("Segera lihat film ${movie.title} di Bioskop terdekat")
                        .startChooser()
                }
            }

            fragmentTvShowBinding.rvTvShow.setHasFixedSize(true)
            fragmentTvShowBinding.rvTvShow.adapter = tvShowAdapter

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            fragmentTvShowBinding.progressBar.visibility = View.VISIBLE
            viewModel.getTvShows().observe(viewLifecycleOwner, Observer {
                tvShowAdapter.differ.submitList(it)
                fragmentTvShowBinding.progressBar.visibility = View.INVISIBLE
            })
        }
    }

}