package com.aidos.roomy_app.ui.admin_ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidos.roomy_app.data.repository.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class AdminMenuViewModel @Inject constructor(
    val userRepository: UserRepository
): ViewModel() {
    // TODO: Implement the ViewModel
}