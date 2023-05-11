package com.aidos.roomy_app.ui.admin_ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.fragment.findNavController
import com.aidos.roomy_app.R
import com.aidos.roomy_app.databinding.FragmentEditRoomBinding
import com.aidos.roomy_app.databinding.FragmentPaymentListBinding
import com.aidos.roomy_app.models.User
import com.aidos.roomy_app.ui.resident_ui.InvoiceFragment
import com.aidos.roomy_app.ui.theme.RoomyMainTheme
import com.aidos.roomy_app.ui.ui_components.InvoiceItemRow
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PaymentListFragment : DaggerFragment() {

    companion object {
        fun newInstance() = PaymentListFragment()
        const val KEY_DORMITORY_ID = "KEY_DORMITORY_ID"
    }

    fun dormitoryIdArgs() = requireArguments().getInt(KEY_DORMITORY_ID)

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: PaymentListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[PaymentListViewModel::class.java]
    }
    var binding: FragmentPaymentListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewModel.loadPayments()
        //viewModel.loadPlaces(dormitoryIdArgs())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentListBinding.inflate(inflater, container, false)
        val binding = binding ?: return null
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = binding ?: return
        val resident = User.Resident(-1, "Aidos", "Alimkhan")
        binding.composeView.setContent {
            val paymentsState by viewModel.paymentsStateFlow.collectAsState()
            val payments = paymentsState.ifEmpty { viewModel.generateFakePayments() }
            RoomyMainTheme {
                Column(
                    modifier = Modifier
                        .scrollable(rememberScrollState(), orientation = Orientation.Vertical)
                        .fillMaxSize())
                {
                    Text(
                        text = stringResource(id = R.string.payment_title) + " list",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h4,
                        color = MaterialTheme.colors.onSurface
                    )
                    payments.forEach { monthlyPayment ->
                        InvoiceItemRow(payment = monthlyPayment, resident = resident) {
                            findNavController().navigate(R.id.action_paymentListFragment_to_invoiceFragment2,
                            Bundle().apply {
                                putSerializable(InvoiceFragment.KEY_PLACE, viewModel.findPlaceByPayment(monthlyPayment.paymentId))
                                putSerializable(InvoiceFragment.KEY_PAYMENT, monthlyPayment)
                            })
                        }
                    }
                }
            }
        }
    }

}