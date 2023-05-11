package com.aidos.roomy_app.ui.admin_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidos.roomy_app.data.repository.DefaultPaymentRepository
import com.aidos.roomy_app.data.repository.PaymentRepository
import com.aidos.roomy_app.data.repository.PlaceRepository
import com.aidos.roomy_app.models.Dormitory
import com.aidos.roomy_app.models.MonthlyPayment
import com.aidos.roomy_app.models.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class PaymentListViewModel @Inject constructor(
    private val paymentRepository: PaymentRepository,
    private val placeRepository: PlaceRepository
) : ViewModel() {


    private val _paymentsStateFlow = MutableStateFlow<List<MonthlyPayment>>(listOf())
    val paymentsStateFlow = _paymentsStateFlow

    private val _placesStateFlow = MutableStateFlow<List<Place>>(listOf())
    val placesStateFlow : StateFlow<List<Place>> = _placesStateFlow

    fun loadPayments(dormitoryId: Int) {
        viewModelScope.launch {
            _paymentsStateFlow.update {
                paymentRepository.getPayments(dormitoryId)
            }
        }
    }
    fun findPlaceByPayment(paymentId: String): Place? {
        return _placesStateFlow.value.find { it.placeId == paymentId.toInt() }
    }

    fun loadPlaces(dormitoryId: Int) {
        viewModelScope.launch {
            _placesStateFlow.update {
                placeRepository.getAllPlacesInDormitory(dormitoryId)
            }
        }
    }
}