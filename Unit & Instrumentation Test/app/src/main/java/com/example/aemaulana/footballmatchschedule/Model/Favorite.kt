package com.example.aemaulana.footballmatchschedule.Model

data class Favorite(
    val id: Long?,
    val eventId: String?, val date: String?,
    val homeTeam: String?, val awayTeam: String?,
    val homeScore: Int?, val awayScore: Int?,
    val homeShot: Int?, val awayShot: Int?,
    val homeGK: String?, val awayGK: String?,
    val homeMF: String?, val awayMF: String?,
    val homeFW: String?, val awayFW: String?,
    val homeSB: String?, val awaySB: String?,
    val homeDF: String?, val awayDF: String?
) {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val DATE: String = "DATE"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val HOME_SHOT: String = "HOME_SHOT"
        const val AWAY_SHOT: String = "AWAY_SHOT"
        const val HOME_GK: String = "HOME_GK"
        const val AWAY_GK: String = "AWAY_GK"
        const val HOME_DF: String = "HOME_DF"
        const val AWAY_DF: String = "AWAY_DF"
        const val HOME_MF: String = "HOME_MF"
        const val AWAY_MF: String = "AWAY_MF"
        const val HOME_FW: String = "HOME_FW"
        const val AWAY_FW: String = "AWAY_FW"
        const val HOME_SB: String = "HOME_SB"
        const val AWAY_SB: String = "AWAY_SB"

    }
}