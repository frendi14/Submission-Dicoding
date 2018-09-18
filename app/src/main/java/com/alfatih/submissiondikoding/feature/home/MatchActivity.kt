package com.alfatih.submissiondikoding.feature.home

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.alfatih.submissiondikoding.R
import com.alfatih.submissiondikoding.feature.detail.DetailActivity
import com.alfatih.submissiondikoding.feature.home.adapter.MatchAdapter
import com.alfatih.submissiondikoding.feature.home.contract.ItemCallback
import com.alfatih.submissiondikoding.feature.home.contract.MatchCallback
import com.alfatih.submissiondikoding.feature.home.model.MatchModel
import com.alfatih.submissiondikoding.feature.home.presenter.MatchPresenter
import kotlinx.android.synthetic.main.activity_match.*
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.startActivity

class MatchActivity : AppCompatActivity(), ItemCallback, MatchCallback {

    private lateinit var adapter: MatchAdapter
    private lateinit var presenter: MatchPresenter
    private val lists = ArrayList<MatchModel>()
    private var paramMatch = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)
        val bundle = intent.extras
        if(bundle != null){
                paramMatch = bundle.getInt("leagues",0)
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        presenter = MatchPresenter(this,this)
        adapter = MatchAdapter(lists,false)
        adapter.setItemOnClick(this)
        recyclerMatch.layoutManager = LinearLayoutManager(this)
        recyclerMatch.adapter = adapter
        navigation.selectedItemId = R.id.navigation_prev_match
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_prev_match -> {
                presenter.getData(MatchPresenter.KEY_PASTMATCH,paramMatch)
                adapter.setIsNext(false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_next_match -> {
                presenter.getData(MatchPresenter.KEY_NEXTMATCH,paramMatch)
                adapter.setIsNext(true)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onItemLeaguesClick(position: Int) {
        val model = lists[position]
        startActivity<DetailActivity>("eventId" to model.idMatch)
    }

    override fun onRefreshList(list: ArrayList<MatchModel>) {
        lists.clear()
        lists.addAll(list)
        adapter.notifyDataSetChanged()
    }

    override fun onShowProgress() {
//        indeterminateProgressDialog("")
    }

    override fun onHideProgress() {

    }
}
