package com.tools.jackofall.di

import com.tools.core.data.shared.PreferenceStorage
import com.tools.jackofall.JackOfAllApplication
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance jackOfAllApplication: JackOfAllApplication): AppComponent
    }

    fun preferenceStorage(): PreferenceStorage

}