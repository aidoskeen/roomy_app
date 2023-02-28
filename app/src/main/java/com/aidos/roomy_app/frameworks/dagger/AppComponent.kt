package com.aidos.roomy_app.frameworks.dagger

import android.app.Application
import com.aidos.roomy_app.frameworks.dagger.subcomponents.ActivityModule
import com.aidos.roomy_app.frameworks.dagger.subcomponents.FragementModule
import com.aidos.roomy_app.frameworks.dagger.subcomponents.ViewModelModule
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
        ViewModelModule::class
    ]
)

interface AppComponent : AndroidInjector<RoomyApplication>