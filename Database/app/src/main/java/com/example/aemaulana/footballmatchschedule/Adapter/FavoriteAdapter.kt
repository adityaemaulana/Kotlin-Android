package com.example.aemaulana.footballmatchschedule.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.aemaulana.footballmatchschedule.MatchUI
import com.example.aemaulana.footballmatchschedule.Model.Favorite
import com.example.aemaulana.footballmatchschedule.R
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import java.text.SimpleDateFormat
import java.util.*

class FavoriteAdapter(private val favorites: List<Favorite>, private val listener: (Favorite) -> Unit) :
    RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(MatchUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = favorites.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) =
        holder.bindItem(favorites[position], listener)

}

class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val dateView = view.find<TextView>(R.id.tv_date)
    private val homeName = view.find<TextView>(R.id.tv_home)
    private val homeScore = view.find<TextView>(R.id.tv_home_score)
    private val awayName = view.find<TextView>(R.id.tv_away)
    private val awayScore = view.find<TextView>(R.id.tv_away_score)

    fun bindItem(favorite: Favorite, listener: (Favorite) -> Unit) {
        homeName.text = favorite.homeTeam
        awayName.text = favorite.awayTeam

        if (favorite.homeScore != -1) homeScore.text = favorite.homeScore?.toString()
        if (favorite.awayScore != -1) awayScore.text = favorite.awayScore?.toString()

        val inputDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(favorite.date)
        val outputDate = SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault()).format(inputDate)
        dateView.text = outputDate.toString()

        itemView.setOnClickListener { listener(favorite) }
    }
}