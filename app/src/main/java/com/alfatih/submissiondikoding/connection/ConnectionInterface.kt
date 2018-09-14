package com.alfatih.submissiondikoding.connection

import com.alfatih.submissiondikoding.feature.home.model.LeaguesModel
import retrofit2.Call
import retrofit2.http.GET

interface ConnectionInterface {

    @GET("search_all_leagues.php?s=Soccer")
    fun getLeagues(): Call<LeaguesModel.LeaguesResponse>

}