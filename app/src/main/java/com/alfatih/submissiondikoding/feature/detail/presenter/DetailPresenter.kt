package com.alfatih.submissiondikoding.feature.detail.presenter

import android.content.Context
import com.alfatih.submissiondikoding.connection.Connection
import com.alfatih.submissiondikoding.feature.detail.contract.DetailCallback
import com.alfatih.submissiondikoding.feature.detail.model.DetailModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter (val context: Context, val callback: DetailCallback) {

    fun getDataDetail(iventId: Int){
        if(Connection.isNetworkAvailable(context)){
            callback.onShowProgress()
            val call: Call<DetailModel.DetailResponse> = Connection.open().getDetail(iventId)
            call.enqueue(object: Callback<DetailModel.DetailResponse>{

                override fun onResponse(call: Call<DetailModel.DetailResponse>?, response: Response<DetailModel.DetailResponse>?) {
                    if(Connection.checkHttpCode(response!!.code())){
                        if(response.body().events.isNotEmpty()){
                            callback.onLoadData(response.body().events[0])
                        }
                    }
                    callback.onHideProgress()
                }

                override fun onFailure(call: Call<DetailModel.DetailResponse>?, t: Throwable?) {
                    callback.onHideProgress()
                }
            })
        }
    }

}