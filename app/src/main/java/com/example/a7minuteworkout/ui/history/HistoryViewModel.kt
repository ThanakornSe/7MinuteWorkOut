package com.example.a7minuteworkout.ui.history

import androidx.lifecycle.*
import com.example.a7minuteworkout.database.HistoryRepository
import com.example.a7minuteworkout.model.HistoryModel
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: HistoryRepository) : ViewModel() {

    val getAllHistory: LiveData<List<HistoryModel>> = repository.allHistory().asLiveData()

    fun insert(historyModel: HistoryModel) =
        viewModelScope.launch { repository.insert(historyModel) }

    fun clearData() = viewModelScope.launch { repository.clearData() }
}


class HistoryViewModelFactory(private val repository: HistoryRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HistoryViewModel(repository) as T
    }
}