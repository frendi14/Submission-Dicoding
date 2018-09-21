package com.alfatih.submissiondikoding.feature.home.presenter

import android.content.Context
import com.alfatih.submissiondikoding.connection.Connection
import com.alfatih.submissiondikoding.feature.home.contract.MatchCallback
import com.alfatih.submissiondikoding.feature.home.model.MatchModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchPresenter(val context: Context): MatchCallback.Presenter {

    private val list = ArrayList<MatchModel>()
    private var callback: MatchCallback.View? = null

    companion object {
        const val KEY_NEXTMATCH = 421
        const val KEY_PASTMATCH = 422
    }

    override fun getData(requset: Int, id: Int) {
        if(Connection.isNetworkAvailable(context)){
            callback?.onShowProgress()
            var call: Call<MatchModel.ListMatchResponse>? = null

            when(requset){
                KEY_NEXTMATCH -> call = Connection.open().getNextmatch(id)
                KEY_PASTMATCH -> call = Connection.open().getPastMatch(id)
            }

            call?.enqueue(object: Callback<MatchModel.ListMatchResponse>{

                override fun onResponse(call: Call<MatchModel.ListMatchResponse>?, response: Response<MatchModel.ListMatchResponse>?) {
                    if(Connection.checkHttpCode(response!!.code())){
                        // data di league tertentu ada ynag null
                        if((response.body().events?.isNotEmpty())){
                            list.clear()
                            list.addAll(response.body().events)
                            callback?.onRefreshList(list, requset)
                        }else{
                            list.clear()
                            callback?.onRefreshList(list, requset)
                        }
                        callback?.onHideProgress()
                    }
                }

                override fun onFailure(call: Call<MatchModel.ListMatchResponse>?, t: Throwable?) {
                    t?.printStackTrace()
                    callback?.onHideProgress()
                }
            })
        }
    }

    override fun onAttach(View: MatchCallback.View) {
        callback = View
    }

    override fun onDetach() {
        callback = null
    }
}