package com.aidos.roomy_app.ui.resident_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aidos.roomy_app.R
import com.aidos.roomy_app.databinding.FragmentRoomsBinding
import com.aidos.roomy_app.models.Dormitory
import com.aidos.roomy_app.models.Room
import com.aidos.roomy_app.models.User
import com.aidos.roomy_app.ui.theme.RoomyMainTheme
import com.aidos.roomy_app.ui.ui_components.RoomItemRow
import com.aidos.roomy_app.ui.ui_components.RoomyTopAppBar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class RoomsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var binding: FragmentRoomsBinding? = null

    companion object {
        const val DORMITORY = "DORMITORY"
    }

    private fun dormitoryArgs() = requireArguments().getSerializable(DORMITORY) as? Dormitory

    private val viewModel: RoomsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[RoomsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoomsBinding.inflate(inflater, container, false)
        val binding = binding ?: return null
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = binding ?: return
        val resident = User.Resident("1", name = "Aidos", surname = "Alimkhan")
        binding.roomsComposeView.setContent {
            RoomyMainTheme {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colors.background)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    RoomyTopAppBar(
                        backgroundColor = MaterialTheme.colors.primary,
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        label = "Roomy",
                        onUserIconClick = { }
                    )

                    Text(
                        modifier = Modifier
                            .padding(25.dp),
                        text = AnnotatedString(stringResource(id = R.string.dorm_item_label)),
                        style = MaterialTheme.typography.h4.copy(
                            color = MaterialTheme.colors.onSurface
                        ),
                        textAlign = TextAlign.Center
                    )
                    val dormitory = dormitoryArgs()
                    if (dormitory == null) {
                        Text(
                            text = stringResource(id = R.string.no_rooms),
                            style = MaterialTheme.typography.subtitle2.copy(
                                color = MaterialTheme.colors.onSurface
                            )
                        )
                    } else {
                        dormitory.rooms.forEach { room ->
                            RoomItemRow(
                                item = room,
                                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                                onItemClicked = { navigateToBooking(room = room, resident = resident) }
                            )

                            Divider(modifier = Modifier.padding(start = 20.dp, end = 20.dp))
                        }
                    }
                }
            }
        }
    }

    private fun navigateToBooking(room: Room, resident: User.Resident) {
        findNavController().navigate(R.id.action_roomsFragment_to_bookingFragment,
            Bundle().apply
            {
                putSerializable(BookingFragment.ROOM, room)
                putSerializable(BookingFragment.RESIDENT, resident)
            }
        )
    }
}