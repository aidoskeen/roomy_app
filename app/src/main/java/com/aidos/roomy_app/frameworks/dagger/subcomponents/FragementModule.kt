package com.aidos.roomy_app.frameworks.dagger.subcomponents

import com.aidos.roomy_app.ui.SplashFragment
import com.aidos.roomy_app.ui.common_ui.LoginFragment
import com.aidos.roomy_app.ui.common_ui.RegistrationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragementModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeRegistrationFragment(): RegistrationFragment

    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment
}