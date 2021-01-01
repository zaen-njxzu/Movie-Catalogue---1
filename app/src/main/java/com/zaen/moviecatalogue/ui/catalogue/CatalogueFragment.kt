package com.zaen.moviecatalogue.ui.catalogue

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.zaen.moviecatalogue.databinding.FragmentCatalogueBinding
import com.zaen.moviecatalogue.pager.TypeMoviePagerAdapter
import com.zaen.moviecatalogue.utils.TypeViewSource

class CatalogueFragment : Fragment() {

    private lateinit var binding: FragmentCatalogueBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCatalogueBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.apply {
            val cataloguePagerAdapter = TypeMoviePagerAdapter(this@CatalogueFragment, TypeViewSource.CATALOGUE)
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