package com.alfatih.submissiondikoding.database

import android.content.ContentValues
import android.content.Context
import com.alfatih.submissiondikoding.feature.home.model.MatchModel
import com.alfatih.submissiondikoding.utils.Constante
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class Database(val ctx: Context){

    private var database: DatabaseHelper = ctx.database

    fun selectFavoriteById(id: String): List<MatchModel>?{
        var result: List<MatchModel>? = null
        try {
            database.use {
                result = select(Constante.KEY_FavoriteTable)
                        .whereSimple(Constante.KEY_IdMatch,id).parseList(classParser())
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
        return result
    }

    fun selectFavorite(): List<MatchModel>?{
        var result: List<MatchModel>? = null
        try {
            database.use {
                result = select(Constante.KEY_FavoriteTable)
                        .parseList(classParser())
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
        return result
    }

    fun insertFavorite(model: MatchModel){
        try {
            database.use {
                val values = ContentValues()
                values.put(Constante.KEY_ID, model.idMatch)
                values.put(Constante.KEY_date, model.date)
                values.put(Constante.KEY_home_Team, model.teamOneName)
                values.put(Constante.KEY_home_Score, model.teamOneScore)
                values.put(Constante.KEY_away_Team, model.teamTwoName)
                values.put(Constante.KEY_away_Score, model.teamTwoScore)
                values.put(Constante.KEY_isNext, model.isNextMatch)
                insert(Constante.KEY_FavoriteTable,null,values)
            }
            ctx.toast("Data has been added")
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun deleteFavorite(idEvent: String){
        try {
            database.use {
                delete(Constante.KEY_FavoriteTable,
                        "(${Constante.KEY_ID} = {id})",
                        "id" to idEvent)
            }
            ctx.toast("data has been removed")
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

}