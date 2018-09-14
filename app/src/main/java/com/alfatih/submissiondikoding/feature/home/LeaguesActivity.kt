package com.alfatih.submissiondikoding.feature.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.alfatih.submissiondikoding.R
import com.alfatih.submissiondikoding.feature.home.adapter.LeaguesAdapter
import com.alfatih.submissiondikoding.feature.home.contract.ItemLeaguesCallback
import com.alfatih.submissiondikoding.feature.home.contract.LeaguesCallback
import com.alfatih.submissiondikoding.feature.home.model.LeaguesModel
import com.alfatih.submissiondikoding.feature.home.presenter.LeaguesPresenter
import kotlinx.android.synthetic.main.activity_leagues.*
import org.jetbrains.anko.startActivity

class LeaguesActivity : AppCompatActivity(), LeaguesCallback, ItemLeaguesCallback {

    private lateinit var adapter: LeaguesAdapter
    private lateinit var presenter: LeaguesPresenter
    private var lists = ArrayList<LeaguesModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leagues)
        adapter = LeaguesAdapter(this,lists)
        adapter.setLeaguesCallback(this)
        presenter = LeaguesPresenter(this, this)
        recyclerLeagues.layoutManager = LinearLayoutManager(this)
        recyclerLeagues.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        presenter.getDataLeagues()
    }

    override fun onRefreshList(list: ArrayList<LeaguesModel>) {
        lists.clear()
        lists.addAll(list)
        adapter.notifyDataSetChanged()
    }

    override fun onShowProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun onHideProgress() {
        progress.visibility = View.GONE
    }

    override fun onItemLeaguesClick(position: Int) {
        startActivity<HomeActivity>("leagues" to position)
    }
}