package com.example.a7minuteworkout.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a7minuteworkout.model.HistoryModel

@Database(entities = [HistoryModel::class], version = 1, exportSchema = false)
abstract class HistoryDB : RoomDatabase() {
    abstract fun historyDao(): HistoryDao

    companion object {
        @Volatile
        private var INSTANCE: HistoryDB? = null

        fun getDB(context: Context): HistoryDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HistoryDB::class.java,
                    "history.db"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}