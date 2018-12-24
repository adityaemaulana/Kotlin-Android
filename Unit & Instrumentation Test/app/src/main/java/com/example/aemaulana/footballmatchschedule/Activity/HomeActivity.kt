package com.example.aemaulana.footballmatchschedule.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.aemaulana.footballmatchschedule.Fragment.FavoriteFragment
import com.example.aemaulana.footballmatchschedule.Fragment.LastMatchFragment
import com.example.aemaulana.footballmatchschedule.Fragment.NextMatchFragment
import com.example.aemaulana.footballmatchschedule.R
import com.example.aemaulana.footballmatchschedule.R.id.*
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                prev_match -> {
                    loadLastMatchsFragment(savedInstanceState)
                }

                next_match -> {
                    loadNextMatchsFragment(savedInstanceState)
                }

                favorites -> {
                    loadFavoritesFragment(savedInstanceState)
                }
            }
            true
        }

        bottom_navigation.selectedItemId = prev_match
    }

    private fun loadLastMatchsFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, LastMatchFragment(), LastMatchFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadNextMatchsFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, NextMatchFragment(), NextMatchFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, FavoriteFragment(), FavoriteFragment::class.java.simpleName)
                .commit()
        }
    }
}