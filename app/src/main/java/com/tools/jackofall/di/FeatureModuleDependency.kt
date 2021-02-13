package com.tools.jackofall.di

import com.tools.core.data.shared.PreferenceStorage
import com.tools.core.data.shared.SharedPrefStorage
import dagger.Binds
import dagger.Module
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FeatureModuleDependency {

    fun preferenceStorage(): PreferenceStorage
}

@Module
@InstallIn(SingletonComponent::class)
abstract class FeatureModule {
    @Binds
    abstract fun getPreferenceStorage(sharedPrefStorage: SharedPrefStorage): PreferenceStorage
}