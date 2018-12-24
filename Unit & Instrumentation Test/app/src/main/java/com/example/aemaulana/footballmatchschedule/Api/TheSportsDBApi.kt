package com.example.aemaulana.footballmatchschedule.Api

import android.net.Uri
import com.example.aemaulana.footballmatchschedule.BuildConfig

object TheSportsDBApi {

    // Konstan untuk id liga, digunakan liga English Premier League
    var LEAGUE_ID = "4328"

    // Endpoint untuk match yang sudah selesai
    fun getLastMatchs(): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("eventspastleague.php")
            .appendQueryParameter("id", LEAGUE_ID)
            .build()
            .toString()
    }

    // Endpoint untuk match yang akan diadakan
    fun getNextMatchs(): String = Uri.parse(BuildConfig.BASE_URL).buildUpon()
        .appendPath("api")
        .appendPath("v1")
        .appendPath("json")
        .appendPath(BuildConfig.TSDB_API_KEY)
        .appendPath("eventsnextleague.php")
        .appendQueryParameter("id", LEAGUE_ID)
        .build()
        .toString()

    fun getTeam(teamName: String?): String = Uri.parse(BuildConfig.BASE_URL).buildUpon()
        .appendPath("api")
        .appendPath("v1")
        .appendPath("json")
        .appendPath(BuildConfig.TSDB_API_KEY)
        .appendPath("searchteams.php")
        .appendQueryParameter("t", teamName)
        .build()
        .toString()
}