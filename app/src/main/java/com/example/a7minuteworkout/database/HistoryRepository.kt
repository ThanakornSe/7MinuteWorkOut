package com.example.a7minuteworkout.database

import com.example.a7minuteworkout.model.HistoryModel
import kotlinx.coroutines.flow.Flow

class HistoryRepository(private val historyDao: HistoryDao) {

    suspend fun insert(history:HistoryModel) = historyDao.insert(history)

    fun allHistory():Flow<List<HistoryModel>> = historyDao.getAllDate()

    suspend fun clearData() = historyDao.clearData()

}