package com.aidos.roomy_app.ui.resident_ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.aidos.roomy_app.models.User
import com.aidos.roomy_app.ui.theme.RoomyMainTheme
import com.aidos.roomy_app.ui.ui_components.BookedRoomForm
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MyRoomFragment : DaggerFragment() {

    private var binding: FragmentMyRoomBinding? = null
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MyRoomViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MyRoomViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadData()
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

        binding.composeView.setContent {
            val myRoomState by viewModel.myRoomState.collectAsState()

            val image = painterResource(id = R.drawable.ic_image_room)
            RoomyMainTheme {
                BookedRoomForm(image = image, room = myRoomState.room, place = myRoomState.place) {
                    findNavController().navigate(R.id.action_myRoomFragment_to_invoiceFragment,
                    Bundle().apply
                     {
                         putSerializable(InvoiceFragment.KEY_PAYMENT, myRoomState.place.monthlyPayment)
                         putSerializable(InvoiceFragment.KEY_PLACE, myRoomState.place)
                     })
                }
            }
        }
    }
}