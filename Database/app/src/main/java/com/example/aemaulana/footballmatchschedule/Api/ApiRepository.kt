package com.example.aemaulana.footballmatchschedule.Api

import java.net.URL

class ApiRepository {
    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}