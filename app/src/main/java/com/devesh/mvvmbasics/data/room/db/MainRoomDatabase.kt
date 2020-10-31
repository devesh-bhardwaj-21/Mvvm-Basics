package com.devesh.mvvmbasics.data.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devesh.mvvmbasics.data.room.dao.MainDao
import com.devesh.mvvmbasics.data.room.entity.ResultsItem
import com.devesh.mvvmbasics.util.databaseVersion

@Database(
    entities = arrayOf(ResultsItem::class),
    version = databaseVersion,
    exportSchema = false
)
abstract class MainRoomDatabase : RoomDatabase() {
    abstract fun mainDao(): MainDao
}