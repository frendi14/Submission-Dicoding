package com.alfatih.submissiondikoding.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.alfatih.submissiondikoding.utils.Constante
import org.jetbrains.anko.db.*

class DatabaseHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx,"football.db",null,1) {

    companion object {
        private var instance: DatabaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(ctx.applicationContext)
            }
            return instance as DatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
                Constante.KEY_FavoriteTable,true,
                Constante.KEY_ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Constante.KEY_IdMatch to INTEGER + UNIQUE,
                Constante.KEY_date to TEXT,
                Constante.KEY_home_Team to TEXT,
                Constante.KEY_home_Score to TEXT,
                Constante.KEY_away_Team to TEXT,
                Constante.KEY_away_Score to TEXT,
                Constante.KEY_isNext to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, old: Int, new: Int) {
        db?.dropTable(Constante.KEY_FavoriteTable,true)
    }
}

val Context.database: DatabaseHelper
    get() = DatabaseHelper.getInstance(applicationContext)