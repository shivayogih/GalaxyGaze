package com.shivayogi.galaxygaze.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shivayogi.galaxygaze.data.local.dao.ApodDao
import com.shivayogi.galaxygaze.data.local.entities.ApodEntity

@Database(entities = [ApodEntity::class], version = 1)
abstract class ApodDatabase : RoomDatabase() {
    abstract fun apodDao(): ApodDao
}