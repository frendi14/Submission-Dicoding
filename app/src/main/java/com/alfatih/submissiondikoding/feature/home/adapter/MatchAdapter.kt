package com.alfatih.submissiondikoding.feature.home.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.alfatih.submissiondikoding.R
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
import com.alfatih.submissiondikoding.utils.DateStringUtils

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
        private val matchClick: CardView = view.find(item_match_click)
        private val teamOnename: TextView = view.find(item_match_team_one_name)
        private val teamOneScor: TextView = view.find(item_match_team_one_score)
        private val teamTwoname: TextView = view.find(item_match_team_two_name)
        private val teamTwoScor: TextView = view.find(item_match_team_two_score)
        private val teamDates: TextView = view.find(item_match_date)

        fun bindItem(model: MatchModel){
            teamOnename.text = model.teamOneName
            teamOneScor.text = model.teamOneScore
            teamTwoname.text = model.teamTwoName
            teamTwoScor.text = model.teamTwoScore
            teamDates.text = DateStringUtils.formatingWithDay(model.date)
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

    fun clear(){
        this.list.clear()
        notifyDataSetChanged()
    }

    class ItemMatchUI: AnkoComponent<ViewGroup>{
        override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui){
            frameLayout {
                lparams(matchParent, wrapContent)
                cardView {
                    id = item_match_click
                    verticalLayout {
                        matchParent
                        wrapContent
                        gravity = Gravity.CENTER

                        textView{
                            gravity = Gravity.CENTER
                            id = item_match_date
                            textColor = ContextCompat.getColor(context, R.color.colorGray)
                            textSize = 17f
                        }

                        view{
                            backgroundColor = ContextCompat.getColor(context,R.color.colorPrimaryDark)
                        }.lparams(matchParent,dip(1)){
                            marginEnd = dip(25)
                            marginStart = dip(25)
                        }

                        linearLayout {
                            gravity = Gravity.CENTER
                            orientation = LinearLayout.HORIZONTAL

                            textView {
                                id = item_match_team_one_name
                                gravity = Gravity.END
                                padding = dip(10)
                                textColor = ContextCompat.getColor(context, R.color.colorGray)
                                textSize = 15f
                            }.lparams(dip(0), wrapContent, weight = 1f)

                            linearLayout {
                                gravity = Gravity.CENTER
                                orientation = LinearLayout.HORIZONTAL

                                textView {
                                    id = item_match_team_one_score
                                    textColor = ContextCompat.getColor(context, R.color.colorPrimaryDark)
                                    textSize = 20f
                                }

                                textView ("VS"){
                                    textColor = ContextCompat.getColor(context, R.color.colorGray)
                                    textSize = 15f
                                }.lparams(wrapContent, wrapContent){
                                    marginStart = dip(10)
                                    marginEnd = dip(10)
                                }

                                textView {
                                    id = item_match_team_two_score
                                    textColor = ContextCompat.getColor(context, R.color.colorPrimaryDark)
                                    textSize = 20f
                                }

                            }.lparams(dip(0), wrapContent, weight = 0.5f)

                            textView {
                                id = item_match_team_two_name
                                gravity = Gravity.START
                                padding = dip(10)
                                textColor = ContextCompat.getColor(context, R.color.colorGray)
                                textSize = 15f
                            }.lparams(dip(0), wrapContent, weight = 1f)

                        }
                    }
                }.lparams(matchParent, wrapContent){
                    bottomMargin = dip(5)
                    marginStart = dip(5)
                    marginEnd = dip(5)
                }
            }
        }
    }

}