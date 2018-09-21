package com.alfatih.submissiondikoding.feature.detail.presenter

import android.content.Context
import com.alfatih.submissiondikoding.connection.Connection
import com.alfatih.submissiondikoding.feature.detail.contract.DetailCallback
import com.alfatih.submissiondikoding.feature.detail.model.DetailModel
import com.alfatih.submissiondikoding.feature.detail.model.TeamModel
import com.alfatih.submissiondikoding.utils.DateStringUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter (val context: Context): DetailCallback.Presenter {

    private var homeURL = ""
    private var awayURL = ""
    private lateinit var model: DetailModel
    private var callback: DetailCallback.View? = null

    override fun getDataDetail(iventId: Int){
        if(Connection.isNetworkAvailable(context)){
            callback?.onShowProgress()
            val call: Call<DetailModel.DetailResponse> = Connection.open().getDetail(iventId)
            call.enqueue(object: Callback<DetailModel.DetailResponse>{

                override fun onResponse(call: Call<DetailModel.DetailResponse>?, response: Response<DetailModel.DetailResponse>?) {
                    if(Connection.checkHttpCode(response!!.code())){
                        // data di events tertentu ada yang null
                        if(response.body().events?.isNotEmpty()){
                            model = response.body().events[0]
                            model.dateEvent = DateStringUtils.formatingWithDay(model.dateEvent)
                            model.strHomeGoalDetails = filter(model.strHomeGoalDetails)
                            model.strHomeLineupDefense = filter(model.strHomeLineupDefense)
                            model.strHomeLineupMidfield = filter(model.strHomeLineupMidfield)
                            model.strHomeLineupForward = filter(model.strHomeLineupForward)
                            model.strHomeLineupSubstitutes = filter(model.strHomeLineupSubstitutes)

                            model.strAwayGoalDetails = filter(model.strAwayGoalDetails)
                            model.strAwayLineupDefense = filter(model.strAwayLineupDefense)
                            model.strAwayLineupMidfield = filter(model.strAwayLineupMidfield)
                            model.strAwayLineupForward = filter(model.strAwayLineupForward)
                            model.strAwayLineupSubstitutes = filter(model.strAwayLineupSubstitutes)

                            getTeamDetailHome(model.idHomeTeam.toInt())
                        }
                    }
                }

                override fun onFailure(call: Call<DetailModel.DetailResponse>?, t: Throwable?) {
                    callback?.onHideProgress()
                }
            })
        }
    }

    private fun getTeamDetailHome(id: Int){
        if(Connection.isNetworkAvailable(context)){
            val call: Call<TeamModel.TeamResponse> = Connection.open().getTeamDetail(id)
            call.enqueue(object: Callback<TeamModel.TeamResponse>{

                override fun onResponse(call: Call<TeamModel.TeamResponse>?, response: Response<TeamModel.TeamResponse>?) {
                    if (Connection.checkHttpCode(response!!.code())){
                        // data di teams tertentu ada yang null
                        if(response.body().teams?.isNotEmpty()){
                            homeURL = response.body().teams[0].teamBadge
                            getTeamDetailAway(model.idAwayTeam.toInt())
                        }
                    }
                }

                override fun onFailure(call: Call<TeamModel.TeamResponse>?, t: Throwable?) {
                    callback?.onHideProgress()
                }
            })
        }
    }

    private fun getTeamDetailAway(id: Int){
        if(Connection.isNetworkAvailable(context)){
            val call: Call<TeamModel.TeamResponse> = Connection.open().getTeamDetail(id)
            call.enqueue(object: Callback<TeamModel.TeamResponse>{

                override fun onResponse(call: Call<TeamModel.TeamResponse>?, response: Response<TeamModel.TeamResponse>?) {
                    if (Connection.checkHttpCode(response!!.code())){
                        // data di teams tertentu ada yang null
                        if(response.body().teams?.isNotEmpty()){
                            awayURL = response.body().teams[0].teamBadge
                            callback?.onLoadData(model,awayURL,homeURL)
                        }
                    }
                    callback?.onHideProgress()
                }

                override fun onFailure(call: Call<TeamModel.TeamResponse>?, t: Throwable?) {
                    callback?.onHideProgress()
                }
            })
        }
    }

    override fun onAttach(View: DetailCallback.View) {
        callback = View
    }

    override fun onDetach() {
        callback = null
    }

    fun filter(input: String?):String{
        return if (input.isNullOrEmpty()){
            ""
        }else{
            input!!.replace(";","\n")
        }
    }

}