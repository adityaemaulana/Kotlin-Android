package com.example.aemaulana.footballmatchschedule.View

import android.widget.ImageView
import com.example.aemaulana.footballmatchschedule.Model.Match
import com.example.aemaulana.footballmatchschedule.Model.Team

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Match>)
    fun showTeam(data: Team, target: ImageView)
}