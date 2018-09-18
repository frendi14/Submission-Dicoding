package com.alfatih.submissiondikoding.feature.home.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

import com.alfatih.submissiondikoding.R.id.item_match_click
import com.alfatih.submissiondikoding.R.id.item_match_date
import com.alfatih.submissiondikoding.R.id.item_match_team_one_name
import com.alfatih.submissiondikoding.R.id.item_match_team_one_score
import com.alfatih.submissiondikoding.R.id.item_match_team_two_score
import com.alfatih.submissiondikoding.R.id.item_match_team_two_name
import com.alfatih.submissiondikoding.feature.home.contract.ItemCallback
import com.alfatih.submissiondikoding.feature.home.model.MatchModel

class MatchAdapter(private var list: MutableList<MatchModel>, private var isNext: Boolean):
        RecyclerView.Adapter<MatchAdapter.ViewHolder>(){

    private var callback: ItemCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder =
            ViewHolder(ItemMatchUI().createView(AnkoContext.create(parent.context,parent)))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val matchClick: CardView = view.find(item_match_click)
        val teamOnename: TextView = view.find(item_match_team_one_name)
        val teamOneScor: TextView = view.find(item_match_team_one_score)
        val teamTwoname: TextView = view.find(item_match_team_two_name)
        val teamTwoScor: TextView = view.find(item_match_team_two_score)
        val teamDates: TextView = view.find(item_match_date)

        fun bindItem(model: MatchModel){
            teamOnename.text = model.teamOneName
            teamOneScor.text = model.teamOneScore
            teamTwoname.text = model.teamTwoName
            teamTwoScor.text = model.teamTwoScore
            teamDates.text = model.date
            matchClick.setOnClickListener {
                callback?.onItemLeaguesClick(adapterPosition)
            }
            when {
                isNext -> {
                    teamOneScor.visibility = View.GONE
                    teamTwoScor.visibility = View.GONE
                }
                else -> {
                    teamOneScor.visibility = View.VISIBLE
                    teamTwoScor.visibility = View.VISIBLE
                }
            }

        }
    }

    fun setItemOnClick(callbacks: ItemCallback){
        this.callback = callbacks
    }

    fun setIsNext(isNext: Boolean){
        this.isNext = isNext
        notifyDataSetChanged()
    }

    class ItemMatchUI: AnkoComponent<ViewGroup>{
        override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui){
            frameLayout {
                lparams(matchParent, wrapContent)
                cardView {
                    id = item_match_click
                    lparams(matchParent, wrapContent)
                    verticalLayout {
                        matchParent
                        wrapContent
                        gravity = Gravity.CENTER
                        textView{
                            gravity = Gravity.CENTER
                            id = item_match_date
                        }
                        linearLayout {
                            gravity = Gravity.CENTER
                            orientation = LinearLayout.HORIZONTAL
                            linearLayout {
                                gravity = Gravity.END
                                orientation = LinearLayout.HORIZONTAL
                                lparams(dip(0), wrapContent, weight = 1f)
                                textView {
                                    id = item_match_team_one_name
                                    padding = dip(10)
                                }
                                textView {
                                    id = item_match_team_one_score
                                }

                            }

                            textView (" vs ")

                            linearLayout {
                                gravity = Gravity.START
                                orientation = LinearLayout.HORIZONTAL
                                lparams(dip(0), wrapContent, weight = 1f)
                                textView {
                                    id = item_match_team_two_score
                                }
                                textView {
                                    id = item_match_team_two_name
                                    padding = dip(10)
                                }
                            }

                        }
                    }
                }
            }
        }
    }

}