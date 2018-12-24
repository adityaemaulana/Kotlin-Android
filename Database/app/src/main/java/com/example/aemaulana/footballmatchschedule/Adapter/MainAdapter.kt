package com.example.aemaulana.footballmatchschedule.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.aemaulana.footballmatchschedule.MatchUI
import com.example.aemaulana.footballmatchschedule.Model.Match
import com.example.aemaulana.footballmatchschedule.R
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import java.text.SimpleDateFormat
import java.util.*

class MainAdapter(private val matchs: List<Match>, private val listener: (Match) -> Unit) :
    RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MatchUI().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }

    override fun getItemCount(): Int = matchs.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindItem(matchs[position], listener)
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val dateView = view.find<TextView>(R.id.tv_date)
    private val homeName = view.find<TextView>(R.id.tv_home)
    private val homeScore = view.find<TextView>(R.id.tv_home_score)
    private val awayName = view.find<TextView>(R.id.tv_away)
    private val awayScore = view.find<TextView>(R.id.tv_away_score)

    fun bindItem(matchs: Match, listener: (Match) -> Unit) {
        homeName.text = matchs.homeTeam
        homeScore.text = matchs.homeScore?.toString()
        awayName.text = matchs.awayTeam
        awayScore.text = matchs.awayScore?.toString()

        val inputDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(matchs.date)
        val outputDate = SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault()).format(inputDate)
        dateView.text = outputDate.toString()

        itemView.setOnClickListener { listener(matchs) }
    }
}

