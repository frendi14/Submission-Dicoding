package com.alfatih.submissiondikoding.feature.home.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.alfatih.submissiondikoding.R
import com.alfatih.submissiondikoding.feature.home.contract.ItemCallback
import com.alfatih.submissiondikoding.feature.home.model.LeaguesModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class LeaguesAdapter(val context: Context, private var list: MutableList<LeaguesModel>):
        RecyclerView.Adapter<LeaguesAdapter.ViewHolder>(){

    private var callback: ItemCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(ItemLeagusUI().createView(AnkoContext.create(parent.context,parent)))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val textLeagues: TextView = view.find(R.id.item_leagues_name)
        private val textLogo: ImageView = view.find(R.id.item_leagues_logo)
        private val leaguesLayout: FrameLayout = view.find(R.id.item_leagues_layout)

        fun bindItem(items: LeaguesModel) {
            textLeagues.text = items.leagues
            Glide.with(context).load(items.logo)
                    .apply(RequestOptions().override(300,200).error(R.drawable.ic_logonotfound))
                    .into(textLogo)
            leaguesLayout.setOnClickListener {
                callback?.onItemLeaguesClick(adapterPosition)
            }
        }
    }

    fun setLeaguesCallback(callback: ItemCallback){
        this.callback = callback
    }

    class ItemLeagusUI: AnkoComponent<ViewGroup>{
        override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
            frameLayout  {
                id = R.id.item_leagues_layout
                lparams(matchParent, wrapContent)
                cardView {
                    padding = dip(5)
                    verticalLayout {
                        lparams(matchParent, wrapContent)
                        gravity = Gravity.CENTER

                        imageView {
                            id = R.id.item_leagues_logo
                        }

                        view{
                            backgroundColor = R.color.colorGray2
                        }.lparams(matchParent,dip(1)){
                            marginEnd = dip(15)
                            marginStart = dip(15)
                        }

                        textView {
                            id = R.id.item_leagues_name
                            textSize = 17f
                            textColor = R.color.colorBlack
                            gravity = Gravity.CENTER
                        }.lparams(matchParent, wrapContent)
                    }
                }.lparams(matchParent, wrapContent){
                    margin = dip(3)
                }
            }
        }

    }

}