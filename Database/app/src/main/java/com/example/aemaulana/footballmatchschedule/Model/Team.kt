package com.example.aemaulana.footballmatchschedule.Model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("strTeam")
    var name: String? = null,

    @SerializedName("strTeamBadge")
    var badge: String? = null
)