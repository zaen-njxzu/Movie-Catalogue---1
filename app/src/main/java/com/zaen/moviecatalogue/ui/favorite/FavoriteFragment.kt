package com.zaen.moviecatalogue.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.zaen.moviecatalogue.databinding.FragmentFavoriteBinding
import com.zaen.moviecatalogue.pager.TypeMoviePagerAdapter
import com.zaen.moviecatalogue.utils.TypeViewSource

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.apply {
            val cataloguePagerAdapter = TypeMoviePagerAdapter(this@FavoriteFragment, TypeViewSource.FAVORITE)
            binding.viewPager.adapter = cataloguePagerAdapter
            binding.apply {
                viewPager.adapter = cataloguePagerAdapter
                viewPager.offscreenPageLimit = cataloguePagerAdapter.itemCount
                viewPager.currentItem = 0
                TabLayoutMediator(tabs, viewPager) { tab, position ->
                    when (position) {
                        0 -> tab.text = getString(TypeMoviePagerAdapter.TAB_TITLES[0])
                        1 -> tab.text = getString(TypeMoviePagerAdapter.TAB_TITLES[1])
                    }
                }.attach()
            }
        }

    }
}