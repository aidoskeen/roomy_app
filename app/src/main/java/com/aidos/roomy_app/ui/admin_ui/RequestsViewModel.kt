package com.aidos.roomy_app.ui.admin_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidos.roomy_app.data.repository.RequestRepository
import com.aidos.roomy_app.enums.RequestStatus
import com.aidos.roomy_app.models.Request
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class RequestsViewModel @Inject constructor(
    private val requestRepository: RequestRepository
): ViewModel() {
    private val _requestsStateFlow: MutableStateFlow<List<Request>> = MutableStateFlow(listOf())
    val requestsStateFlow = _requestsStateFlow
    fun loadRequests() {
        viewModelScope.launch {
            _requestsStateFlow.update {
                requestRepository.getAllRequests()
            }
        }
    }

    fun setRequestStatus(request: Request, requestStatus: RequestStatus) {
        val newRequest = request.copy(requestStatus = requestStatus)
        viewModelScope.launch {
            requestRepository.updateRequestStatus(newRequest)
        }
    }
}