package com.alfatih.submissiondikoding.connection

import com.alfatih.submissiondikoding.feature.detail.model.DetailModel
import com.alfatih.submissiondikoding.feature.home.model.LeaguesModel
import com.alfatih.submissiondikoding.feature.home.model.MatchModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ConnectionInterface {

    @GET("search_all_leagues.php?s=Soccer")
    fun getLeagues(): Call<LeaguesModel.LeaguesResponse>

    @GET("eventsnextleague.php")
    fun getNextmatch(@Query(value = "id", encoded = true) param:Int): Call<MatchModel.ListMatchResponse>

    @GET("eventspastleague.php")
    fun getPastMatch(@Query(value = "id", encoded = true) param: Int): Call<MatchModel.ListMatchResponse>

    @GET("lookupevent.php")
    fun getDetail(@Query(value = "id", encoded = true) param: Int): Call<DetailModel.DetailResponse>
}