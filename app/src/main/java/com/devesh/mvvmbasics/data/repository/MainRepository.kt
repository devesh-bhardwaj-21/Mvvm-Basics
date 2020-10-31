package com.devesh.mvvmbasics.data.repository

import com.devesh.mvvmbasics.data.api.ApiService
import com.devesh.mvvmbasics.data.room.db.MainRoomDatabase
import com.devesh.mvvmbasics.data.room.entity.ResultsItem
import com.devesh.mvvmbasics.util.API_OFFSET
import com.devesh.mvvmbasics.util.API_ORDER_BY_OPENING_DATE
import com.devesh.mvvmbasics.util.API_TYPE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    private val database: MainRoomDatabase
) {

    private val coroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    init {
        coroutineScope.launch { getReviewsFromServer() }
    }

    fun getMovieReviews(): Flow<List<ResultsItem>> {
        return database.mainDao().getMovieReviews()
    }

    private suspend fun getReviewsFromServer(): List<ResultsItem>? {
        val movieReviews =
            apiService.getMovieReviews(API_TYPE, API_OFFSET, API_ORDER_BY_OPENING_DATE)
        movieReviews.results.let {
            database.mainDao().deleteAll()
            database.mainDao().insert(it)
        }
        return movieReviews.results
    }
}