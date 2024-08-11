package com.shivayogi.galaxygaze.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "apod_table")
data class ApodEntity(
    @PrimaryKey val date: String,
    val title: String? = null,
    val explanation: String? = null,
    val url: String? = null,
    val copyright: String? = null,
    val hdurl: String? = null,
    val media_type: String? = null,
    val service_version: String? = null

)
