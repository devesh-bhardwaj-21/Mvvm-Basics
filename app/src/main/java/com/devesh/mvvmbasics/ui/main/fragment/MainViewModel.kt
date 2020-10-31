package com.devesh.mvvmbasics.ui.main.fragment

import androidx.lifecycle.*
import com.devesh.mvvmbasics.data.repository.MainRepository
import com.devesh.mvvmbasics.data.room.entity.ResultsItem
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MainViewModel(private val repository: MainRepository) : ViewModel() {

    private val _uiStateData = MutableLiveData<UiState>(UiState.Loading)
    val uiStateLiveData get(): LiveData<UiState> = _uiStateData

    init {
        val exceptionHandler =
            CoroutineExceptionHandler { _, _ -> _uiStateData.postValue(UiState.Error) }
        viewModelScope.launch(exceptionHandler) {
            repository.getMovieReviews()
                .flowOn(Dispatchers.IO)
                .collect { results ->
                    _uiStateData.postValue(UiState.Success(results))
                }
        }
    }

    sealed class UiState {
        object Loading : UiState()
        class Success(val data: List<ResultsItem>) : UiState()
        object Error : UiState()
    }

    class MainViewModelFactory @Inject constructor(
        private val repository: MainRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }
    }
}