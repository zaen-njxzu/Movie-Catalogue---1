package com.zaen.moviecatalogue.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.zaen.moviecatalogue.R
import com.zaen.moviecatalogue.databinding.ActivityHomeBinding
import com.zaen.moviecatalogue.ui.catalogue.CatalogueFragment
import com.zaen.moviecatalogue.ui.favorite.FavoriteFragment


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)

        val mFragmentManager = supportFragmentManager
        val mHomeFragment = CatalogueFragment()
        val fragment = mFragmentManager.findFragmentByTag(CatalogueFragment::class.java.simpleName)
        if (fragment !is CatalogueFragment) {
            mFragmentManager
                .beginTransaction()
                .add(
                    activityHomeBinding.frameContainer.id,
                    mHomeFragment,
                    CatalogueFragment::class.java.simpleName
                )
                .commit()
        }

        activityHomeBinding.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.action_list_catalogue -> {
                    val currentFragment = mFragmentManager.findFragmentByTag(CatalogueFragment::class.java.simpleName)
                    if(currentFragment !is CatalogueFragment) {
                        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                        transaction.replace(activityHomeBinding.frameContainer.id, CatalogueFragment())
                        transaction.commit()
                    }
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_list_favorite -> {
                    val currentFragment = mFragmentManager.findFragmentByTag(FavoriteFragment::class.java.simpleName)
                    if(currentFragment !is FavoriteFragment) {
                        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                        transaction.replace(activityHomeBinding.frameContainer.id, FavoriteFragment())
                        transaction.commit()
                    }
                    return@setOnNavigationItemSelectedListener true
                }
            }

            return@setOnNavigationItemSelectedListener true
        }

    }
}