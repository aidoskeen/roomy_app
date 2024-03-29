package com.aidos.roomy_app.ui.resident_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aidos.roomy_app.R
import com.aidos.roomy_app.databinding.FragmentBookingBinding
import com.aidos.roomy_app.databinding.FragmentLoginBinding
import com.aidos.roomy_app.enums.RequestStatus
import com.aidos.roomy_app.enums.RoomSize
import com.aidos.roomy_app.enums.RoomType
import com.aidos.roomy_app.models.Place
import com.aidos.roomy_app.models.Room
import com.aidos.roomy_app.models.User
import com.aidos.roomy_app.ui.theme.RoomyMainTheme
import com.aidos.roomy_app.ui.ui_components.MessageBox
import com.aidos.roomy_app.ui.ui_components.RoomBookingForm
import dagger.android.support.DaggerFragment
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

class BookingFragment : DaggerFragment() {

    companion object {
        const val ROOM = "ROOM"
        const val RESIDENT = "RESIDENT"
        const val DORMITORY_ID = "DORMITORY_ID"
    }
    private fun dormitoryArgs() = requireArguments().getInt(DORMITORY_ID)
    private fun roomArgs() = requireArguments().getSerializable(ROOM) as? Room
    private fun residentArgs() = requireArguments().getSerializable(RESIDENT) as? User.Resident

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var binding: FragmentBookingBinding? = null
    private val viewModel: BookingViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[BookingViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookingBinding.inflate(inflater, container, false)
        val binding = binding ?: return null
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = binding ?: return
        val resident = residentArgs()
        val room = roomArgs()
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = Date()
        val currentDate = formatter.format(date).toString()

        binding.composeView.setContent {
            RoomyMainTheme {
                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                    val (messageRef, formRef) = createRefs()
                    val uiState by viewModel.uiState.collectAsState()
                    if (uiState.showMessage)
                        MessageBox(
                            modifier = Modifier.constrainAs(messageRef) {
                                linkTo(
                                    parent.start,
                                    parent.top,
                                    parent.end,
                                    parent.bottom
                                )
                            },
                            message = uiState.message
                        )

                    if (resident != null) {
                        if (room != null)
                            RoomBookingForm(
                                modifier = Modifier.constrainAs(formRef) {
                                    linkTo(
                                        parent.start,
                                        parent.top,
                                        parent.end,
                                        parent.bottom
                                    )
                                },
                                resident = resident,
                                dormitoryId = dormitoryArgs(),
                                room = room,
                                date = currentDate
                            ) { place ->
                                val updatedPlace = place.copy(
                                    livingResident = resident,
                                    requestStatus = RequestStatus.PENDING
                                )

                                viewModel.startBooking(updatedPlace)
                            }
                    } else
                        Text(text = stringResource(id = R.string.user_not_authorized))
                }
            }
        }

        viewModel.onFinish.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
    }
}