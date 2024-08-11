package com.shivayogi.galaxygaze.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shivayogi.galaxygaze.data.local.entities.ApodEntity

@Dao
interface ApodDao {

    @Query("SELECT * FROM apod_table WHERE date = :date")
    suspend fun getApodByDate(date: String): ApodEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApod(apod: ApodEntity)

}