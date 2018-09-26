package com.alfatih.submissiondikoding.feature.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatDelegate
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import com.alfatih.submissiondikoding.R
import com.alfatih.submissiondikoding.feature.detail.contract.DetailCallback
import com.alfatih.submissiondikoding.feature.detail.model.DetailModel
import com.alfatih.submissiondikoding.feature.detail.presenter.DetailPresenter
import com.alfatih.submissiondikoding.utils.invisible
import com.alfatih.submissiondikoding.utils.visible
import com.alfatih.submissiondikoding.R.menu.add_to_favorite
import com.alfatih.submissiondikoding.feature.home.model.MatchModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.startActivity

class DetailActivity : AppCompatActivity(), DetailCallback.View {

    private lateinit var presenter: DetailPresenter
    private var params = 0
    private var isNext = 0
    private var menuItem: Menu? = null
    private var isFavorite = false
    private var matchModel: MatchModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        params = intent.extras?.getInt("eventId",0) as Int
        isNext = intent.extras?.getInt("isNext",0) as Int
        presenter = DetailPresenter(this)
        presenter.onAttach(this)
    }

    override fun onStart() {
        super.onStart()
        presenter.getDataDetail(params)
    }

    override fun onLoadData(model: DetailModel, away: String, home: String) {
        matchModel = MatchModel(
                model.idEvent.toInt(),model.dateEvent,model.strHomeTeam,model.intHomeScore,
                model.strAwayTeam,model.intAwayScore,isNext)

        presenter.checkingData(model.idEvent.toInt())

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

    override fun isExist(value: Boolean) {
        isFavorite = value
        setFavorite()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(add_to_favorite,menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.add_to_favorite -> {
                when {
                    !isFavorite -> {
                        val params = matchModel
                        params?.let {
                            presenter.insertFavorite(params)
                        }
                    }
                    else -> presenter.deleteFavorite(params)
                }
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            R.id.home -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_start_added_favorite)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_add_favorite)
    }

    override fun onStop() {
        super.onStop()
        presenter.onDetach()
    }

    override fun onShowProgress() {
        progress.visible()
    }

    override fun onHideProgress() {
        progress.invisible()
    }
}
