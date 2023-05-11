package com.aidos.roomy_app.ui.resident_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidos.roomy_app.data.repository.UserRepository
import com.aidos.roomy_app.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    fun createUser(user: User) {
        viewModelScope.launch {
            userRepository.createUser(user)

        }
    }
}