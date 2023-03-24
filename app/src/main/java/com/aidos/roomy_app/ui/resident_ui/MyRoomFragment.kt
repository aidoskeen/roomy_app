package com.aidos.roomy_app.ui.resident_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aidos.roomy_app.R
import com.aidos.roomy_app.databinding.FragmentMyRoomBinding
import com.aidos.roomy_app.enums.PaymentStatus
import com.aidos.roomy_app.enums.RoomSize
import com.aidos.roomy_app.enums.RoomType
import com.aidos.roomy_app.models.MonthlyPayment
import com.aidos.roomy_app.models.Place
import com.aidos.roomy_app.models.Room
import com.aidos.roomy_app.ui.theme.RoomyMainTheme
import com.aidos.roomy_app.ui.ui_components.BookedRoomForm
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MyRoomFragment : DaggerFragment() {

    companion object {

    }

    private var binding: FragmentMyRoomBinding? = null
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MyRoomViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MyRoomViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyRoomBinding.inflate(inflater, container, false)
        val binding = binding ?: return null
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = binding ?: return
        val payment = MonthlyPayment(paymentId = "1",month = "December", paymentStatus = PaymentStatus.PAID, dueDate = "2023/03/23")
        val place = Place(price = 100L, monthlyPayment = payment)
        val room = Room(11, RoomType.SINGLE, RoomSize.SMALL, listOf(place))

        binding.composeView.setContent {
            val image = painterResource(id = R.drawable.ic_launcher_foreground)
            RoomyMainTheme {
                BookedRoomForm(image = image, room = room, place = place) {
                    findNavController().navigate(R.id.action_myRoomFragment_to_invoiceFragment)
                }
            }
        }
    }
}