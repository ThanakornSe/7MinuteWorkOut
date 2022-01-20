package com.example.a7minuteworkout

import android.app.Application
import com.example.a7minuteworkout.database.HistoryDB
import com.example.a7minuteworkout.database.HistoryRepository

class WorkOutApplication:Application() {

    private val database:HistoryDB by lazy {
        HistoryDB.getDB(this)
    }

    val repository:HistoryRepository by lazy {
        HistoryRepository(database.historyDao())
    }
}