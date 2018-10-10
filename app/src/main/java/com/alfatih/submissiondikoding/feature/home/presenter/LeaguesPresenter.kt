package com.alfatih.submissiondikoding.feature.home.presenter

import com.alfatih.submissiondikoding.connection.Connection
import com.alfatih.submissiondikoding.feature.home.contract.LeaguesCallback
import com.alfatih.submissiondikoding.feature.home.model.LeaguesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class LeaguesPresenter: LeaguesCallback.Presenter {

    private val list = ArrayList<LeaguesModel>()
    private var callback: LeaguesCallback.View? = null

    override fun getDataLeagues(){
        callback?.onShowProgress()
        val call: Call<LeaguesModel.LeaguesResponse> = Connection.open().getLeagues()

        call.enqueue(object: Callback<LeaguesModel.LeaguesResponse>{

            override fun onResponse(call: Call<LeaguesModel.LeaguesResponse>?, response: Response<LeaguesModel.LeaguesResponse>) {
                if(Connection.checkHttpCode(response.code())){
                    val responseList = response.body()
                    list.clear()
                    responseList.countrys?.let { it ->
                        it.isNotEmpty().let {
                            list.addAll(responseList.countrys)
                        }
                    }
                    callback?.onRefreshList(list)
                }
                callback?.onHideProgress()
            }

            override fun onFailure(call: Call<LeaguesModel.LeaguesResponse>?, t: Throwable?) {
                callback?.onHideProgress()
            }

        })
    }

    override fun onAttach(View: LeaguesCallback.View) {
        callback = View
    }

    override fun onDetach() {
        callback = null
    }

}