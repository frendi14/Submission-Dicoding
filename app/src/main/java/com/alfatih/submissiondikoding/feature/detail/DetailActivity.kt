package com.alfatih.submissiondikoding.feature.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.alfatih.submissiondikoding.R
import com.alfatih.submissiondikoding.feature.detail.contract.DetailCallback
import com.alfatih.submissiondikoding.feature.detail.model.DetailModel
import com.alfatih.submissiondikoding.feature.detail.presenter.DetailPresenter
import com.alfatih.submissiondikoding.utils.invisible
import com.alfatih.submissiondikoding.utils.visible
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailCallback {

    private lateinit var presenter: DetailPresenter
    private var params = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        params = intent.extras?.getInt("eventId",0)!!
        presenter = DetailPresenter(this,this)
    }

    override fun onStart() {
        super.onStart()
        presenter.getDataDetail(params)
    }

    override fun onLoadData(model: DetailModel, away: String, home: String) {

        detail_score_home.text = model.intHomeScore
        detail_date.text = model.dateEvent
        detail_name_home.text = model.strHomeTeam
        detail_formasi_home.text = model.strHomeFormation
        detail_goals_home.text = model.strHomeGoalDetails
        detail_shots_home.text = model.intHomeShots
        detail_GoalKeeper_home.text = model.strHomeLineupGoalkeeper
        detail_def_home.text = model.strHomeLineupDefense
        detail_mid_home.text = model.strHomeLineupMidfield
        detail_forward_home.text = model.strHomeLineupForward
        detail_subs_home.text = model.strHomeLineupSubstitutes

        detail_score_away.text = model.intAwayScore
        detail_name_away.text = model.strAwayTeam
        detail_formasi_away.text = model.strAwayFormation
        detail_goals_away.text = model.strAwayGoalDetails
        detail_shots_away.text = model.intAwayShots
        detail_GoalKeeper_away.text = model.strAwayLineupGoalkeeper
        detail_def_away.text = model.strAwayLineupDefense
        detail_mid_away.text = model.strAwayLineupMidfield
        detail_forward_away.text = model.strAwayLineupForward
        detail_subs_away.text = model.strAwayLineupSubstitutes

        if(!TextUtils.isEmpty(home)){
            Glide.with(this).load(home)
                    .apply(RequestOptions().override(100,100).error(R.drawable.ic_logonotfound).placeholder(R.drawable.ic_logonotfound))
                    .into(detail_logo_home)
        }

        if(!TextUtils.isEmpty(away)){
            Glide.with(this).load(away)
                    .apply(RequestOptions().override(100,100).error(R.drawable.ic_logonotfound).placeholder(R.drawable.ic_logonotfound))
                    .into(detail_logo_away)
        }

    }

    override fun onShowProgress() {
        progress.visible()
    }

    override fun onHideProgress() {
        progress.invisible()
    }
}
