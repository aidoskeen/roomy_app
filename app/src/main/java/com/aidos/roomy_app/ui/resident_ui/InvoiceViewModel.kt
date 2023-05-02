package com.aidos.roomy_app.ui.resident_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aidos.roomy_app.data.repository.PlaceRepository
import com.aidos.roomy_app.enums.PaymentStatus
import com.aidos.roomy_app.enums.RoomSize
import com.aidos.roomy_app.enums.RoomType
import com.aidos.roomy_app.models.MonthlyPayment
import com.aidos.roomy_app.models.Place
import com.aidos.roomy_app.models.Room
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class InvoiceViewModel @Inject constructor(
    val placeRepository: PlaceRepository
) : ViewModel() {


    fun createDummyPayment() : MonthlyPayment = MonthlyPayment(paymentId = "1","December", paymentStatus = PaymentStatus.PAID, dueDate = "2023/03/23")

    fun createDummyPlace(payment: MonthlyPayment) : Place = Place(price = 100L, monthlyPayment = payment)
}