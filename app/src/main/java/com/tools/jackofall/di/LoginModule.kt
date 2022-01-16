package com.tools.jackofall.di

import com.tools.core.network.JackOfAllAPI
import com.tools.core.network.RetrofitProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Provides
    fun provideJackOfAllAPI(
    ): JackOfAllAPI {
        return RetrofitProvider.getINSTANCE().retrofit.create(JackOfAllAPI::class.java)
    }
}
