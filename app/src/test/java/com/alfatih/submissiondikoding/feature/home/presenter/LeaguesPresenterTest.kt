package com.alfatih.submissiondikoding.feature.home.presenter

import com.alfatih.submissiondikoding.BuildConfig
import com.alfatih.submissiondikoding.connection.ConnectionInterface
import com.alfatih.submissiondikoding.feature.home.model.LeaguesModel
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class LeaguesPresenterTest {

    @Test
    fun getDataLeagues() {
        val mockServer = MockWebServer()
        mockServer.enqueue(MockResponse().setBody("{countrys:[{idLeague:4406,strLeague:Argentinian Primera Division,strLogo:https://www.thesportsdb.com/images/media/league/logo/sppyvw1422243698.png},{idLeague:4356,strLeague:Australian A-League,strLogo:https://www.thesportsdb.com/images/media/league/logo/tuvpvv1421844752.png},{idLeague:4338,strLeague:Belgian Jupiler League,strLogo:https://www.thesportsdb.com/images/media/league/logo/ttxvrs1422402616.png},{idLeague:4404,strLeague:Brazilian Brasileirao Serie B,strLogo:https://www.thesportsdb.com/images/media/league/logo/qpvxqq1422460318.png},{idLeague:4351,strLeague:Brazilian Brasileirao,strLogo:https://www.thesportsdb.com/images/media/league/logo/quvpxu1422459374.png}]}"))
        mockServer.start()
        val retrofit = Retrofit.Builder()
                .baseUrl(mockServer.url(BuildConfig.BASE_URL).toString())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val services: ConnectionInterface = retrofit.create(ConnectionInterface::class.java)
        val call: Call<LeaguesModel.LeaguesResponse> = services.getLeagues()
        assertTrue(call.execute() != null)
        mockServer.shutdown()
    }
}