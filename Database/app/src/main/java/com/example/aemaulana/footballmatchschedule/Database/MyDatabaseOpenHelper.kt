package com.example.aemaulana.footballmatchschedule.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.aemaulana.footballmatchschedule.Model.Favorite
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance =
                        MyDatabaseOpenHelper(ctx.applicationContext)
            }

            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            Favorite.TABLE_FAVORITE, true,
            Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorite.EVENT_ID to TEXT + UNIQUE,
            Favorite.DATE to TEXT,
            Favorite.HOME_TEAM to TEXT,
            Favorite.AWAY_TEAM to TEXT,
            Favorite.HOME_SCORE to INTEGER,
            Favorite.AWAY_SCORE to INTEGER,
            Favorite.HOME_SHOT to INTEGER,
            Favorite.AWAY_SHOT to INTEGER,
            Favorite.HOME_GK to TEXT,
            Favorite.AWAY_GK to TEXT,
            Favorite.HOME_DF to TEXT,
            Favorite.AWAY_DF to TEXT,
            Favorite.HOME_MF to TEXT,
            Favorite.AWAY_MF to TEXT,
            Favorite.HOME_FW to TEXT,
            Favorite.AWAY_FW to TEXT,
            Favorite.HOME_SB to TEXT,
            Favorite.AWAY_SB to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(
        applicationContext
    )
