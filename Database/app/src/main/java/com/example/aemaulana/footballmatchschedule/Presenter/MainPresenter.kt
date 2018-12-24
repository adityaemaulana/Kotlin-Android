package com.example.aemaulana.footballmatchschedule.Presenter

import android.widget.ImageView
import com.example.aemaulana.footballmatchschedule.Api.ApiRepository
import com.example.aemaulana.footballmatchschedule.Api.TheSportsDBApi
import com.example.aemaulana.footballmatchschedule.MatchResponse
import com.example.aemaulana.footballmatchschedule.TeamResponse
import com.example.aemaulana.footballmatchschedule.View.MainView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(
    private val view: MainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val endPoint: String
) {

    fun getMatchList() {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository.doRequest(endPoint),
                MatchResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showMatchList(data.events)
            }
        }
    }

    fun getTeam(teamName: String?, target: ImageView) {
        doAsync {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportsDBApi.getTeam(teamName)),
                TeamResponse::class.java
            )

            uiThread {
                view.showTeam(data.teams[0], target)
            }
        }
    }
}