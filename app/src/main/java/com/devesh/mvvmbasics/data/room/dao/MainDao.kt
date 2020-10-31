package com.devesh.mvvmbasics.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.devesh.mvvmbasics.data.room.entity.ResultsItem
import com.devesh.mvvmbasics.util.movieReviewTableName
import kotlinx.coroutines.flow.Flow

@Dao
interface MainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(resultItemList: List<ResultsItem>?)

    @Query("SELECT * from $movieReviewTableName ORDER BY headline ASC")
    fun getMovieReviews(): Flow<List<ResultsItem>>

    @Query("DELETE FROM $movieReviewTableName")
    suspend fun deleteAll()

}