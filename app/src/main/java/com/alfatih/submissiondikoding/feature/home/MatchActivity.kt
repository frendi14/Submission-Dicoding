package com.alfatih.submissiondikoding.feature.home

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.alfatih.submissiondikoding.R
import com.alfatih.submissiondikoding.feature.detail.DetailActivity
import com.alfatih.submissiondikoding.feature.home.adapter.MatchAdapter
import com.alfatih.submissiondikoding.feature.home.contract.ItemCallback
import com.alfatih.submissiondikoding.feature.home.contract.MatchCallback
import com.alfatih.submissiondikoding.feature.home.model.MatchModel
import com.alfatih.submissiondikoding.feature.home.presenter.MatchPresenter
import com.alfatih.submissiondikoding.utils.EsspressoIdleing
import com.alfatih.submissiondikoding.utils.invisible
import com.alfatih.submissiondikoding.utils.visible
import kotlinx.android.synthetic.main.activity_match.*
import org.jetbrains.anko.startActivity

class MatchActivity : AppCompatActivity(), ItemCallback, MatchCallback.View {

    private lateinit var adapter: MatchAdapter
    private lateinit var presenter: MatchPresenter
    private val lists = ArrayList<MatchModel>()
    private var keyNext = 0
    private var paramMatch = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)
        val bundle = intent.extras
        when {
            bundle != null -> paramMatch = bundle.getInt("leagues",0)
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        presenter = MatchPresenter(this)
        presenter.onAttach(this)
        adapter = MatchAdapter(lists,false)
        adapter.setItemOnClick(this)
        recyclerMatch.layoutManager = LinearLayoutManager(this)
        recyclerMatch.itemAnimator = DefaultItemAnimator()
        recyclerMatch.adapter = adapter
        navigation.selectedItemId = R.id.navigation_prev_match
    }

    override fun onResume() {
        super.onResume()
        presenter.onAttach(this)
        when {
            navigation.selectedItemId == R.id.navigation_favorite -> {
                adapter.clear()
                presenter.getFavorite()
            }
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_prev_match -> {
                adapter.clear()
                presenter.getData(MatchPresenter.KEY_PASTMATCH,paramMatch)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_next_match -> {
                adapter.clear()
                presenter.getData(MatchPresenter.KEY_NEXTMATCH,paramMatch)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorite -> {
                adapter.clear()
                presenter.getFavorite()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onItemLeaguesClick(position: Int) {
        val model = lists[position]
        startActivity<DetailActivity>("eventId" to model.idMatch, "isNext" to keyNext)
    }

    override fun onRefreshList(list: ArrayList<MatchModel>, next: Int) {
        EsspressoIdleing.decrement()
        lists.clear()
        lists.addAll(list)
        adapter.notifyDataSetChanged()
        keyNext = next
        when(next){
            MatchPresenter.KEY_NEXTMATCH -> adapter.setIsNext(true)
            MatchPresenter.KEY_PASTMATCH -> adapter.setIsNext(false)
        }
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
