package com.shivayogi.galaxygaze.data.repositories

import android.content.Context
import android.net.ConnectivityManager
import com.shivayogi.galaxygaze.data.local.dao.ApodDao
import com.shivayogi.galaxygaze.data.local.entities.ApodEntity
import com.shivayogi.galaxygaze.data.remote.NasaApodApi
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import com.shivayogi.galaxygaze.BuildConfig
import com.shivayogi.galaxygaze.utils.NetworkUtils.isNetworkAvailable
import javax.inject.Named


class ApodRepository @Inject constructor(
    private val api: NasaApodApi,
    private val dao: ApodDao,
    @ApplicationContext private val context: Context,
    @Named("apiKey") private val apiKey: String
) {

    suspend fun getApod(date: String): ApodEntity? {
        return if (isNetworkAvailable(context)) {
            val response = api.getApod(apiKey).body()
            val apodEntity = response?.let { ApodEntity(date, it.title, it.explanation, it.url, it.copyright, it.hdurl, it.media_type, it.service_version) }
            if (apodEntity != null) {
                dao.insertApod(apodEntity)
            }
            apodEntity ?: dao.getApodByDate(date)
        } else {
            dao.getApodByDate(date)
        }
    }



}
