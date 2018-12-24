package com.example.aemaulana.footballmatchschedule.Activity

import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import com.example.aemaulana.footballmatchschedule.Api.ApiRepository
import com.example.aemaulana.footballmatchschedule.Model.Favorite
import com.example.aemaulana.footballmatchschedule.Model.Match
import com.example.aemaulana.footballmatchschedule.Model.Team
import com.example.aemaulana.footballmatchschedule.Presenter.MainPresenter
import com.example.aemaulana.footballmatchschedule.R
import com.example.aemaulana.footballmatchschedule.R.color.colorAccent
import com.example.aemaulana.footballmatchschedule.R.drawable.ic_add_to_favorites
import com.example.aemaulana.footballmatchschedule.R.drawable.ic_added_to_favorites
import com.example.aemaulana.footballmatchschedule.R.menu.detail_menu
import com.example.aemaulana.footballmatchschedule.View.MainView
import com.example.aemaulana.footballmatchschedule.Database.database
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity(), MainView {

    private lateinit var date: TextView

    private lateinit var homeName: TextView
    private lateinit var homeScore: TextView
    private lateinit var homeImage: ImageView

    private lateinit var homeShot: TextView
    private lateinit var homeGK: TextView
    private lateinit var homeDF: TextView
    private lateinit var homeMF: TextView
    private lateinit var homeFW: TextView
    private lateinit var homeSB: TextView

    private lateinit var awayName: TextView
    private lateinit var awayScore: TextView
    private lateinit var awayImage: ImageView

    private lateinit var awayShot: TextView
    private lateinit var awayGK: TextView
    private lateinit var awayDF: TextView
    private lateinit var awayMF: TextView
    private lateinit var awayFW: TextView
    private lateinit var awaySB: TextView

    private lateinit var scrollView: ScrollView
    private lateinit var presenter: MainPresenter

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        scrollView = scrollView {
            lparams(matchParent, matchParent)

            linearLayout {
                lparams(matchParent, matchParent)
                padding = dip(16)
                orientation = LinearLayout.VERTICAL

                date = textView {
                    textSize = 13F
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textColor = ContextCompat.getColor(context, colorAccent)
                }

                linearLayout {
                    lparams(matchParent, wrapContent) { bottomMargin = dip(8) }
                    orientation = LinearLayout.HORIZONTAL
                    weightSum = 7F

                    linearLayout {
                        lparams(width = wrapContent, height = wrapContent, weight = 2F)
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER_HORIZONTAL

                        homeImage = imageView()
                            .lparams(width = dip(100), height = dip(100)) {
                                bottomMargin = dip(8)
                            }

                        homeName = textView {
                            textSize = 16F
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }
                    }

                    homeScore = textView {
                        textSize = 30F
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                        typeface = Typeface.DEFAULT_BOLD
                        gravity = Gravity.CENTER_VERTICAL
                    }.lparams(width = dip(0), height = dip(100), weight = 1F)

                    textView("vs") {
                        textSize = 20F
                        gravity = Gravity.CENTER_VERTICAL
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams(width = 0, height = dip(100), weight = 1F)

                    awayScore = textView {
                        textSize = 30F
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                        typeface = Typeface.DEFAULT_BOLD
                        gravity = Gravity.CENTER_VERTICAL
                    }.lparams(width = dip(0), height = dip(100), weight = 1F)

                    linearLayout {
                        lparams(width = wrapContent, height = wrapContent, weight = 2F)
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER_HORIZONTAL

                        awayImage = imageView()
                            .lparams(width = dip(100), height = dip(100)) {
                                bottomMargin = dip(8)
                            }

                        awayName = textView {
                            textSize = 16F
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }
                    }
                }

                linearLayout {
                    lparams(matchParent, wrapContent) { bottomMargin = dip(8) }
                    orientation = LinearLayout.HORIZONTAL
                    weightSum = 3F

                    homeShot = textView {
                        textSize = 16F
                        gravity = Gravity.CENTER
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                    }.lparams(width = 0, height = wrapContent, weight = 1F)

                    textView("shots") {
                        textSize = 16F
                        gravity = Gravity.CENTER
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        textColor = ContextCompat.getColor(context, colorAccent)
                    }.lparams(width = 0, height = wrapContent, weight = 1F)

                    awayShot = textView {
                        textSize = 16F
                        gravity = Gravity.CENTER
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                    }.lparams(width = 0, height = wrapContent, weight = 1F)
                }


                // GoalKeeper
                linearLayout {
                    lparams(matchParent, wrapContent) { bottomMargin = dip(8) }
                    orientation = LinearLayout.HORIZONTAL
                    weightSum = 3F

                    homeGK = textView {
                        textSize = 16F
                        gravity = Gravity.CENTER
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                    }.lparams(width = 0, height = wrapContent, weight = 1F)

                    textView("Goalkeeper") {
                        textSize = 16F
                        gravity = Gravity.TOP
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        textColor = ContextCompat.getColor(context, colorAccent)
                    }.lparams(width = 0, height = wrapContent, weight = 1F)

                    awayGK = textView {
                        textSize = 16F
                        gravity = Gravity.CENTER
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                    }.lparams(width = 0, height = wrapContent, weight = 1F)
                }

                // Defense
                linearLayout {
                    lparams(matchParent, wrapContent) { bottomMargin = dip(8) }
                    orientation = LinearLayout.HORIZONTAL
                    weightSum = 3F

                    homeDF = textView {
                        textSize = 16F
                        gravity = Gravity.CENTER
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                    }.lparams(width = 0, height = wrapContent, weight = 1F)

                    textView("Defense") {
                        textSize = 16F
                        gravity = Gravity.TOP
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        textColor = ContextCompat.getColor(context, colorAccent)
                    }.lparams(width = 0, height = wrapContent, weight = 1F)

                    awayDF = textView {
                        textSize = 16F
                        gravity = Gravity.CENTER
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                    }.lparams(width = 0, height = wrapContent, weight = 1F)
                }

                // Midfield
                linearLayout {
                    lparams(matchParent, wrapContent) { bottomMargin = dip(8) }
                    orientation = LinearLayout.HORIZONTAL
                    weightSum = 3F

                    homeMF = textView {
                        textSize = 16F
                        gravity = Gravity.CENTER
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                    }.lparams(width = 0, height = wrapContent, weight = 1F)

                    textView("Midfield") {
                        textSize = 16F
                        gravity = Gravity.TOP
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        textColor = ContextCompat.getColor(context, colorAccent)
                    }.lparams(width = 0, height = wrapContent, weight = 1F)

                    awayMF = textView {
                        textSize = 16F
                        gravity = Gravity.CENTER
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                    }.lparams(width = 0, height = wrapContent, weight = 1F)
                }

                // Forward
                linearLayout {
                    lparams(matchParent, wrapContent) { bottomMargin = dip(8) }
                    orientation = LinearLayout.HORIZONTAL
                    weightSum = 3F

                    homeFW = textView {
                        textSize = 16F
                        gravity = Gravity.CENTER
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                    }.lparams(width = 0, height = wrapContent, weight = 1F)

                    textView("Forward") {
                        textSize = 16F
                        gravity = Gravity.TOP
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        textColor = ContextCompat.getColor(context, colorAccent)
                    }.lparams(width = 0, height = wrapContent, weight = 1F)

                    awayFW = textView {
                        textSize = 16F
                        gravity = Gravity.CENTER
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                    }.lparams(width = 0, height = wrapContent, weight = 1F)
                }

                // Substitutes
                linearLayout {
                    lparams(matchParent, wrapContent) { bottomMargin = dip(8) }
                    orientation = LinearLayout.HORIZONTAL
                    weightSum = 3F

                    homeSB = textView {
                        textSize = 16F
                        gravity = Gravity.CENTER
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                    }.lparams(width = 0, height = wrapContent, weight = 1F)

                    textView("Substitution") {
                        textSize = 16F
                        gravity = Gravity.TOP
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        textColor = ContextCompat.getColor(context, colorAccent)
                    }.lparams(width = 0, height = wrapContent, weight = 1F)

                    awaySB = textView {
                        textSize = 16F
                        gravity = Gravity.CENTER
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                    }.lparams(width = 0, height = wrapContent, weight = 1F)
                }
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intentTrigger = intent
        id = intentTrigger.getStringExtra("eventId")
        favoriteState()

        initUI(intentTrigger)
    }

    fun initUI(intent: Intent) {
        val inputDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(intent.getStringExtra("date"))
        val outputDate = SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault()).format(inputDate)
        date.text = outputDate.toString()

        homeName.text = intent.getStringExtra("homeName")

        val hScore = intent.getIntExtra("homeScore", -1)
        if (hScore != -1) homeScore.text = hScore.toString()

        val hShot = intent.getIntExtra("homeShot", -1)
        if (hShot != -1) homeShot.text = hShot.toString()

        homeGK.text = intent.getStringExtra("homeGK")?.replace("; ", "\n", true)
        homeDF.text = intent.getStringExtra("homeDF")?.replace("; ", "\n", true)
        homeMF.text = intent.getStringExtra("homeMF")?.replace("; ", "\n", true)
        homeFW.text = intent.getStringExtra("homeFW")?.replace("; ", "\n", true)
        homeSB.text = intent.getStringExtra("homeSB")?.replace("; ", "\n", true)

        awayName.text = intent.getStringExtra("awayName")

        val aScore = intent.getIntExtra("awayScore", -1)
        if (aScore != -1) awayScore.text = aScore.toString()

        val aShot = intent.getIntExtra("awayShot", -1)
        if (aShot != -1) awayShot.text = aShot.toString()

        awayGK.text = intent.getStringExtra("awayGK")?.replace("; ", "\n", true)
        awayDF.text = intent.getStringExtra("awayDF")?.replace("; ", "\n", true)
        awayMF.text = intent.getStringExtra("awayMF")?.replace("; ", "\n", true)
        awayFW.text = intent.getStringExtra("awayFW")?.replace("; ", "\n", true)
        awaySB.text = intent.getStringExtra("awaySB")?.replace("; ", "\n", true)

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson, "")
        presenter.getTeam(intent.getStringExtra("homeName"), homeImage)
        presenter.getTeam(intent.getStringExtra("awayName"), awayImage)
    }

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun showMatchList(data: List<Match>) {}

    override fun showTeam(data: Team, target: ImageView) {
        Picasso.get().load(data.badge).into(target)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home -> {
                finish()
                true
            }

            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.EVENT_ID to intent.getStringExtra("eventId"),
                    Favorite.DATE to intent.getStringExtra("date"),
                    Favorite.HOME_TEAM to intent.getStringExtra("homeName"),
                    Favorite.AWAY_TEAM to intent.getStringExtra("awayName"),
                    Favorite.HOME_SCORE to intent.getIntExtra("homeScore", -1),
                    Favorite.AWAY_SCORE to intent.getIntExtra("awayScore", -1),
                    Favorite.HOME_SHOT to intent.getIntExtra("homeShot", -1),
                    Favorite.AWAY_SHOT to intent.getIntExtra("awayShot", -1),
                    Favorite.HOME_GK to intent.getStringExtra("homeGK"),
                    Favorite.AWAY_GK to intent.getStringExtra("awayGK"),
                    Favorite.HOME_DF to intent.getStringExtra("homeDF"),
                    Favorite.AWAY_DF to intent.getStringExtra("awayDF"),
                    Favorite.HOME_MF to intent.getStringExtra("homeMF"),
                    Favorite.AWAY_MF to intent.getStringExtra("awayMF"),
                    Favorite.HOME_FW to intent.getStringExtra("homeFW"),
                    Favorite.AWAY_FW to intent.getStringExtra("awayFW"),
                    Favorite.HOME_SB to intent.getStringExtra("homeSB"),
                    Favorite.AWAY_SB to intent.getStringExtra("awaySB")
                )
            }

            scrollView.snackbar(getString(R.string.add_to_favorite_snackbar)).show()
        } catch (e: SQLiteConstraintException) {
            scrollView.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(
                    Favorite.TABLE_FAVORITE, "(EVENT_ID = {id})",
                    "id" to id
                )
                scrollView.snackbar(getString(R.string.removed_favorite_snackbar)).show()
            }
        } catch (e: SQLiteConstraintException) {
            scrollView.snackbar(e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs(
                    "(EVENT_ID = {id})",
                    "id" to id
                )

            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}
