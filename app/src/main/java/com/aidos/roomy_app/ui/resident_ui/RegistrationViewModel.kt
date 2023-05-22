package com.aidos.roomy_app.ui.resident_ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidos.roomy_app.data.repository.UserRepository
import com.aidos.roomy_app.enums.HostActionStatus
import com.aidos.roomy_app.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {
    val onRegistrationResult = MutableLiveData<Boolean>()
    val onSuccess = MutableLiveData<Unit>()
    fun createUser(user: User) {
        viewModelScope.launch {
            val registrationStatus = userRepository.createUser(user)
            if (registrationStatus == HostActionStatus.SUCCESS) {
                onRegistrationResult.postValue(true)
                delay(1000)
                onSuccess.postValue(Unit)
            }
            else onRegistrationResult.postValue(false)
        }
    }
}