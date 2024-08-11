package com.shivayogi.galaxygaze.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.shivayogi.galaxygaze.BuildConfig
import com.shivayogi.galaxygaze.data.local.dao.ApodDao
import com.shivayogi.galaxygaze.data.local.db.ApodDatabase
import com.shivayogi.galaxygaze.data.remote.NasaApodApi
import com.shivayogi.galaxygaze.data.repositories.ApodRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): ApodDatabase =
        Room.databaseBuilder(app, ApodDatabase::class.java, "apod_db").build()

    @Provides
    @Singleton
    fun provideApodDao(db: ApodDatabase): ApodDao = db.apodDao()


    @Provides
    @Named("apiKey")
    fun provideApiKey(): String {
        return BuildConfig.ApiKey
    }

    @Provides
    @Singleton
    fun provideApodRepository(
        api: NasaApodApi,
        dao: ApodDao,
        @ApplicationContext context: Context,
        @Named("apiKey") apiKey: String
    ): ApodRepository {
        return ApodRepository(api, dao, context, apiKey)
    }
}