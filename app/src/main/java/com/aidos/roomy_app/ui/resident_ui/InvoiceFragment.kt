package com.aidos.roomy_app.ui.resident_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.aidos.roomy_app.databinding.FragmentInvoiceBinding
import com.aidos.roomy_app.enums.PaymentStatus
import com.aidos.roomy_app.enums.RoomSize
import com.aidos.roomy_app.enums.RoomType
import com.aidos.roomy_app.models.MonthlyPayment
import com.aidos.roomy_app.models.Place
import com.aidos.roomy_app.models.Room
import com.aidos.roomy_app.models.User
import com.aidos.roomy_app.ui.theme.RoomyMainTheme
import com.aidos.roomy_app.ui.ui_components.InvoiceForm
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class InvoiceFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var binding: FragmentInvoiceBinding? = null
    private val viewModel: InvoiceViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[InvoiceViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInvoiceBinding.inflate(inflater, container, false)
        val binding = binding ?: return null
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = binding ?: return
        val payment = MonthlyPayment(paymentId = "1","December", paymentStatus = PaymentStatus.PAID, dueDate = "2023/03/23")
        val place = Place(price = 100L, monthlyPayment = payment)
        val room = Room(11, RoomType.SINGLE, RoomSize.SMALL, listOf(place))
        val resident = User.Resident(1, "Aidos", "Alimkhan")
        binding.invComposeView.setContent {
            RoomyMainTheme {
                InvoiceForm(payment = payment, resident = resident, place = place, room = room)
            }
        }
    }
}