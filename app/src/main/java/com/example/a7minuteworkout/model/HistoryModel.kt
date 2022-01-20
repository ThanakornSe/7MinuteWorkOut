package com.example.a7minuteworkout.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class HistoryModel(
    @PrimaryKey(autoGenerate = false)
    val date:String
)
