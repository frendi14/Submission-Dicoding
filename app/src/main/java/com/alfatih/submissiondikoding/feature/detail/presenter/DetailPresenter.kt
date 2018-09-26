package com.alfatih.submissiondikoding.feature.detail.presenter

import android.content.Context
import com.alfatih.submissiondikoding.connection.Connection
import com.alfatih.submissiondikoding.database.Database
import com.alfatih.submissiondikoding.feature.detail.contract.DetailCallback
import com.alfatih.submissiondikoding.feature.detail.model.DetailModel
import com.alfatih.submissiondikoding.feature.detail.model.TeamModel
import com.alfatih.submissiondikoding.feature.home.model.MatchModel
import com.alfatih.submissiondikoding.utils.DateStringUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter (val context: Context): DetailCallback.Presenter {

    private var homeURL = ""
    private var awayURL = ""
    private lateinit var model: DetailModel
    private var callback: DetailCallback.View? = null
    private val database = Database(context)

    override fun getDataDetail(iventId: Int){
        if(Connection.isNetworkAvailable(context)){
            callback?.onShowProgress()
            val call: Call<DetailModel.DetailResponse> = Connection.open().getDetail(iventId)
            call.enqueue(object: Callback<DetailModel.DetailResponse>{

                override fun onResponse(call: Call<DetailModel.DetailResponse>?, response: Response<DetailModel.DetailResponse>) {
                    if(Connection.checkHttpCode(response.code())){
                        val responseList = response.body()
                        responseList.events?.let { it ->
                            it.isNotEmpty().let {
                                model = responseList.events[0]
                                model.dateEvent = DateStringUtils.formatingWithDay(model.dateEvent)

                                model.strHomeGoalDetails = filterString(model.strHomeGoalDetails)
                                model.strHomeLineupDefense = filterString(model.strHomeLineupDefense)
                                model.strHomeLineupMidfield = filterString(model.strHomeLineupMidfield)
                                model.strHomeLineupForward = filterString(model.strHomeLineupForward)
                                model.strHomeLineupSubstitutes = filterString(model.strHomeLineupSubstitutes)
                                model.intHomeScore = filterInteger(model.intHomeScore)
                                model.intHomeShots = filterInteger(model.intHomeShots)

                                model.strAwayGoalDetails = filterString(model.strAwayGoalDetails)
                                model.strAwayLineupDefense = filterString(model.strAwayLineupDefense)
                                model.strAwayLineupMidfield = filterString(model.strAwayLineupMidfield)
                                model.strAwayLineupForward = filterString(model.strAwayLineupForward)
                                model.strAwayLineupSubstitutes = filterString(model.strAwayLineupSubstitutes)
                                model.intAwayScore = filterInteger(model.intAwayScore)
                                model.intAwayShots = filterInteger(model.intAwayShots)
                            }
                        }
                        getTeamDetailHome(model.idHomeTeam.toInt())
                    }
                }

                override fun onFailure(call: Call<DetailModel.DetailResponse>?, t: Throwable?) {
                    callback?.onHideProgress()
                }
            })
        }
    }

    override fun checkingData(id: Int) {
        val result = database.selectFavoriteById(id)
        callback?.isExist(result.isNotEmpty())
    }

    override fun insertFavorite(model: MatchModel) {
        database.insertFavorite(model)
    }

    override fun deleteFavorite(idIvent: Int) {
        database.deleteFavorite(idIvent.toString())
    }

    private fun getTeamDetailHome(id: Int){
        if(Connection.isNetworkAvailable(context)){
            val call: Call<TeamModel.TeamResponse> = Connection.open().getTeamDetail(id)
            call.enqueue(object: Callback<TeamModel.TeamResponse>{

                override fun onResponse(call: Call<TeamModel.TeamResponse>?, response: Response<TeamModel.TeamResponse>) {
                    if (Connection.checkHttpCode(response.code())){
                        val responseList = response.body()
                        responseList.teams?.let {
                            homeURL = it[0].teamBadge
                        }
                        getTeamDetailAway(model.idAwayTeam.toInt())
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

                override fun onResponse(call: Call<TeamModel.TeamResponse>?, response: Response<TeamModel.TeamResponse>) {
                    if (Connection.checkHttpCode(response.code())){
                        val listResponse = response.body()
                        listResponse.teams?.let {
                            awayURL = it[0].teamBadge
                        }
                        callback?.onLoadData(model,awayURL,homeURL)
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

    fun filterString(input: String?):String{
         return input?.replace(";","\n") ?: ""
    }

    fun filterInteger(input: String?): String{
        return input ?: "0"
    }

}