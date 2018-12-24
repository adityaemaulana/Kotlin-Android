package com.example.aemaulana.footballmatchschedule.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.example.aemaulana.footballmatchschedule.Activity.DetailActivity
import com.example.aemaulana.footballmatchschedule.Adapter.FavoriteAdapter
import com.example.aemaulana.footballmatchschedule.Model.Favorite
import com.example.aemaulana.footballmatchschedule.R
import com.example.aemaulana.footballmatchschedule.Database.database
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavoriteFragment : Fragment() {
    private lateinit var viewContainer: View
    private lateinit var listFavorite: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar

    private var favorites: MutableList<Favorite> = mutableListOf()

    private lateinit var adapter: FavoriteAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavoriteAdapter(favorites) {
            context?.startActivity<DetailActivity>(
                "eventId" to it.eventId,
                "date" to it.date,
                "homeName" to it.homeTeam,
                "homeScore" to it.homeScore,
                "homeShot" to it.homeShot,
                "homeGK" to it.homeGK,
                "homeDF" to it.homeDF,
                "homeMF" to it.homeMF,
                "homeFW" to it.homeFW,
                "homeSB" to it.homeSB,
                "awayName" to it.awayTeam,
                "awayScore" to it.awayScore,
                "awayShot" to it.awayShot,
                "awayGK" to it.awayGK,
                "awayDF" to it.awayDF,
                "awayMF" to it.awayMF,
                "awayFW" to it.awayFW,
                "awaySB" to it.awaySB
            )
        }

        listFavorite.adapter = adapter
        swipeRefresh.onRefresh {
            showFavorite()
        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun showFavorite() {
        favorites.clear()
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewContainer = UI {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                swipeRefresh = swipeRefreshLayout {
                    setColorSchemeResources(
                        R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light
                    )

                    relativeLayout {
                        lparams(width = matchParent, height = wrapContent)

                        listFavorite = recyclerView {
                            lparams(matchParent, wrapContent)
                            id = R.id.rv_match
                            layoutManager = LinearLayoutManager(context)
                        }
                    }
                }
            }
        }.view


        return viewContainer
    }


}
