package com.aidos.roomy_app.frameworks.dagger.subcomponents

import com.aidos.roomy_app.activities.AdministrationActivity
import com.aidos.roomy_app.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

class ActivityModule {
    @Module
    abstract class ActivityBindingModule {
        @ContributesAndroidInjector
        abstract fun contributeMainActivity(): MainActivity

        @ContributesAndroidInjector
        abstract fun contributeAdminitrationActivity(): AdministrationActivity
    }
}