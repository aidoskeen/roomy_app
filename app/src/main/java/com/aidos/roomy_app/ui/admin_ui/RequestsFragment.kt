package com.aidos.roomy_app.ui.admin_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.aidos.roomy_app.R
import com.aidos.roomy_app.databinding.FragmentRequestsBinding
import com.aidos.roomy_app.enums.RequestStatus
import com.aidos.roomy_app.enums.RoomSize
import com.aidos.roomy_app.enums.RoomType
import com.aidos.roomy_app.models.Dormitory
import com.aidos.roomy_app.models.Request
import com.aidos.roomy_app.models.Room
import com.aidos.roomy_app.models.User
import com.aidos.roomy_app.ui.theme.RoomyMainTheme
import com.aidos.roomy_app.ui.ui_components.RequestItem
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class RequestsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var binding: FragmentRequestsBinding? = null

    private val viewModel: RequestsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[RequestsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRequestsBinding.inflate(inflater, container, false)
        val binding = binding ?: return null
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = binding ?: return
        val resident = User.Resident(1, name = "Aidos", surname = "Alimkhan")
        val room1 = Room(1, RoomType.DOUBLE, RoomSize.SMALL, listOf(), "Regular room")


        val dormitory = Dormitory(
            dormitoryId = 11,
            address = "Sauletekio 25",
            rooms = listOf(room1),
            "VGTU"
        )
        val requests = listOf<Request>(
            Request(
                1,
                resident,
                room = room1,
                dormitory = dormitory,
                requestStatus = RequestStatus.NONE
            ),
            Request(
                2,
                resident,
                room = room1,
                dormitory = dormitory,
                requestStatus = RequestStatus.NONE
            ),
        )
        binding.composeView.setContent {
            val requestTitle = stringResource(id = R.string.request_management)
            RoomyMainTheme {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier
                            .padding(25.dp),
                        text = AnnotatedString(requestTitle),
                        style = MaterialTheme.typography.h4.copy(
                            color = MaterialTheme.colors.onSurface
                        )
                    )
                    requests.forEach { request ->
                        RequestItem(
                            request = request,
                            onAcceptClicked = { /*TODO*/ },
                            onRejectClicked = { /*TODO*/ }
                        )
                    }
                }
            }
        }
    }

}