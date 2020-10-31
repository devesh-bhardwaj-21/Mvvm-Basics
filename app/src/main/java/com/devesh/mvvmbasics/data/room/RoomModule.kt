package com.devesh.mvvmbasics.data.room

import android.content.Context
import androidx.room.Room
import com.devesh.mvvmbasics.data.room.dao.MainDao
import com.devesh.mvvmbasics.data.room.db.MainRoomDatabase
import com.devesh.mvvmbasics.util.movieReviewDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RoomModule {

    @Provides
    @Singleton
    fun getDatabase(
        context: Context,
    ): MainRoomDatabase {
        return Room.databaseBuilder(
            context,
            MainRoomDatabase::class.java,
            movieReviewDataBase
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun getMainDatabase(database: MainRoomDatabase): MainDao =
        database.mainDao()
}