package com.example.a7minuteworkout.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.a7minuteworkout.model.HistoryModel
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Insert
    suspend fun insert(historyModel: HistoryModel)

    @Query("SELECT * FROM history_table")
    fun getAllDate(): Flow<List<HistoryModel>>

    @Query("DELETE FROM history_table")
    suspend fun clearData()
}