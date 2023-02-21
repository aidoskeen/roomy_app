package com.aidos.roomy_app.frameworks.dagger.subcomponents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aidos.roomy_app.activities.MainViewModel
import com.aidos.roomy_app.frameworks.dagger.ViewModelFactory
import com.aidos.roomy_app.ui.common_ui.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel



}