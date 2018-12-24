package com.example.aemaulana.footballmatchschedule.Activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.example.aemaulana.footballmatchschedule.Adapter.ViewPagerAdapter
import com.example.aemaulana.footballmatchschedule.R
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.support.v4.viewPager
import org.jetbrains.anko.verticalLayout
import org.jetbrains.anko.wrapContent

class MainActivity : AppCompatActivity() {

    private lateinit var tablayout: TabLayout
    private lateinit var viewpager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        verticalLayout {
            lparams(width = matchParent, height = matchParent)

            tablayout = tabLayout {
                lparams(matchParent, wrapContent)
            }

            viewpager = viewPager {
                id = R.id.viewpager
            }.lparams(matchParent, matchParent)
        }

        val viewPagerAdapter =
            ViewPagerAdapter(supportFragmentManager)
        viewpager.adapter = viewPagerAdapter
        tablayout.setupWithViewPager(viewpager)
    }
}
