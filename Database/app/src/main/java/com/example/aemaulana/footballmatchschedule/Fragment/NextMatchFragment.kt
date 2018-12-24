package com.example.aemaulana.footballmatchschedule.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.example.aemaulana.footballmatchschedule.Activity.DetailActivity
import com.example.aemaulana.footballmatchschedule.Adapter.MainAdapter
import com.example.aemaulana.footballmatchschedule.Api.ApiRepository
import com.example.aemaulana.footballmatchschedule.Api.TheSportsDBApi
import com.example.aemaulana.footballmatchschedule.Model.Match
import com.example.aemaulana.footballmatchschedule.Model.Team
import com.example.aemaulana.footballmatchschedule.Presenter.MainPresenter
import com.example.aemaulana.footballmatchschedule.R
import com.example.aemaulana.footballmatchschedule.View.MainView
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout


class NextMatchFragment : Fragment(), MainView {
    override fun showTeam(data: Team, target: ImageView) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var viewContainer: View
    private lateinit var listMatch: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar

    private var matchs: MutableList<Match> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter

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

                        listMatch = recyclerView {
                            lparams(matchParent, wrapContent)
                            id = R.id.rv_match
                            layoutManager = LinearLayoutManager(context)
                        }

                        progressBar = progressBar().lparams { centerHorizontally() }
                    }
                }
            }
        }.view

        adapter = MainAdapter(matchs) {
            startActivity<DetailActivity>(
                "eventId" to it.idEvent,
                "date" to it.date,
                "homeName" to it.homeTeam,
                "homeScore" to it.homeScore,
                "homeShot" to it.homeShot,
                "homeGK" to it.homeGoalKeeper,
                "homeDF" to it.homeDefense,
                "homeMF" to it.homeMidfield,
                "homeFW" to it.homeForward,
                "homeSB" to it.homeSubstitute,

                "awayName" to it.awayTeam,
                "awayScore" to it.awayScore,
                "awayShot" to it.awayShot,
                "awayGK" to it.awayGoalKeeper,
                "awayDF" to it.awayDefense,
                "awayMF" to it.awayMidfield,
                "awayFW" to it.awayForward,
                "awaySB" to it.awaySubstitute
            )
        }

        listMatch.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(
            this,
            request,
            gson,
            TheSportsDBApi.getNextMatchs()
        )

        swipeRefresh.onRefresh {
            presenter.getMatchList()
        }

        presenter.getMatchList()

        return viewContainer
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showMatchList(data: List<Match>) {
        swipeRefresh.isRefreshing = false
        matchs.clear()
        matchs.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
