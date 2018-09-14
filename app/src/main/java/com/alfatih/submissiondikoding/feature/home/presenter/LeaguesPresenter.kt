package com.alfatih.submissiondikoding.feature.home.presenter

import android.content.Context
import com.alfatih.submissiondikoding.connection.Connection
import com.alfatih.submissiondikoding.feature.home.contract.LeaguesCallback
import com.alfatih.submissiondikoding.feature.home.model.LeaguesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class LeaguesPresenter(val context: Context, val callback: LeaguesCallback) {
    private val list = ArrayList<LeaguesModel>()

    fun getDataLeagues(){
        if (Connection.isNetworkAvailable(context)){
            callback.onShowProgress()
            val call: Call<LeaguesModel.LeaguesResponse> = Connection.open().getLeagues()
            call.enqueue(object: Callback<LeaguesModel.LeaguesResponse>{

                override fun onResponse(call: Call<LeaguesModel.LeaguesResponse>?, response: Response<LeaguesModel.LeaguesResponse>?) {
                    if(Connection.checkHttpCode(response!!.code())){
                        if (response.body().list.size > 0){
                            list.clear()
                            list.addAll(response.body().list)
                            callback.onHideProgress()
                            callback.onRefreshList(list)
                        }
                    }
                }

                override fun onFailure(call: Call<LeaguesModel.LeaguesResponse>?, t: Throwable?) {
                    callback.onHideProgress()
                }

            })
        }
    }

}