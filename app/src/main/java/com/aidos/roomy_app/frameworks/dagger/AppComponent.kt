package com.aidos.roomy_app.frameworks.dagger

import android.app.Application
import com.aidos.roomy_app.frameworks.dagger.subcomponents.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule.ActivityBindingModule::class,
        FragementModule::class,
        ViewModelModule::class,
        RepositoryModule::class,
        CoroutinesModule::class,
        DataSourceModule::class
    ]
)

interface AppComponent : AndroidInjector<RoomyApplication>