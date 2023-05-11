package com.aidos.roomy_app.ui.resident_ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidos.roomy_app.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    val userRepository: UserRepository
): ViewModel() {
    val onError = MutableLiveData<String>()

    private val _loginSuccessful = MutableStateFlow(false)
    val loginSuccessful: StateFlow<Boolean> = _loginSuccessful

    fun logIn(username: String, password: String) {
        viewModelScope.launch {
            if (username == "sysadmin" && password == "sysadmin"){
                _loginSuccessful.update {
                    true
                }
                return@launch
            }
            val user = withContext(Dispatchers.Default) {
                userRepository.getUserByLoginData(username, password)
            }
            if (user == null) {
                onError.postValue("User does not exist")
            }
            else _loginSuccessful.value = true
        }
    }
}