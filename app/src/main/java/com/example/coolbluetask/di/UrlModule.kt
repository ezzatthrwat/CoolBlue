package com.example.coolbluetask.di

import com.example.coolbluetask.BuildConfig
import com.example.core.util.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UrlModule {

    @Singleton
    @Provides
    fun provideBaseUrl() = BaseUrl(BuildConfig.BASE_URL)
}
