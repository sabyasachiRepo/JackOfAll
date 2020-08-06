package com.tools.jackofall.di

import android.content.Context
import com.tools.core.data.shared.PreferenceStorage
import com.tools.core.data.shared.SharedPrefStorage

import com.tools.jackofall.JackOfAllApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: JackOfAllApplication): Context = application

    @Singleton
    @Provides
    fun providePreferenceStorage(context: Context): PreferenceStorage =
            SharedPrefStorage(context)
}