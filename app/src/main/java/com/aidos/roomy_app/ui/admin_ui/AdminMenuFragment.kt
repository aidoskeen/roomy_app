package com.aidos.roomy_app.ui.admin_ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.fragment.findNavController
import com.aidos.roomy_app.R
import com.aidos.roomy_app.databinding.FragmentAdminMenuBinding
import com.aidos.roomy_app.enums.RoomSize
import com.aidos.roomy_app.enums.RoomType
import com.aidos.roomy_app.models.Dormitory
import com.aidos.roomy_app.models.Room
import com.aidos.roomy_app.ui.resident_ui.RoomsFragment
import com.aidos.roomy_app.ui.theme.RoomyMainTheme
import com.aidos.roomy_app.ui.ui_components.MenuOption
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AdminMenuFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var binding: FragmentAdminMenuBinding? = null
    private val viewModel: AdminMenuViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[AdminMenuViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdminMenuBinding.inflate(inflater, container, false)
        val binding = binding ?: return null
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = binding ?: return
        val room1 = Room(1, RoomType.DOUBLE, RoomSize.SMALL, listOf(), "Regular room")
        val room2 = Room(2, RoomType.SINGLE, RoomSize.MEDIUM, listOf(), "Regular room")
        val room3 = Room(3, RoomType.TRIPLE, RoomSize.BIG, listOf(), "Regular room")
        val dormitory = Dormitory(
            dormitoryId = 1,
            address = "Sauletekio 25",
            rooms = listOf(room1, room2, room3),
            "VGTU"
        )

        binding.composeView.setContent { 
            RoomyMainTheme {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .padding(25.dp),
                        text = AnnotatedString(stringResource(id = R.string.administration_menu_title)),
                        style = MaterialTheme.typography.h4.copy(
                            color = MaterialTheme.colors.onSurface
                        )
                    )
                    val dividerColor = MaterialTheme.colors.onSurface
                    val dividerModifier = Modifier.padding(horizontal = 18.dp)
                    MenuOption(
                        text = stringResource(id = R.string.make_announcement),
                        onClick = {
                            findNavController().navigate(R.id.action_adminMenuFragment_to_announceFragment)
                        }
                    )

                    Divider(modifier = dividerModifier, color = dividerColor)

                    MenuOption(
                        text = stringResource(id = R.string.check_requests),
                        onClick = { findNavController().navigate(R.id.action_adminMenuFragment_to_requestsFragment,
                            Bundle().apply {
                                putSerializable(RequestsFragment.KEY_ADMIN_DORMITORY, dormitory)
                            }
                        ) }
                    )

                    Divider(color = dividerColor, modifier = dividerModifier)

                    MenuOption(
                        text = stringResource(id = R.string.room_management),
                        onClick = {  findNavController().navigate(R.id.action_adminMenuFragment_to_manageRoomsFragment,
                            Bundle().apply
                            {
                                putSerializable(ManageRoomsFragment.DORMITORY, dormitory)  }
                        ) }
                    )

                    Divider(modifier = dividerModifier, color = dividerColor)
                    
                    MenuOption(
                        text = stringResource(id = R.string.view_payments)
                    ) {
                        findNavController().navigate(R.id.action_adminMenuFragment_to_paymentListFragment,
                        Bundle().apply
                         { putSerializable(PaymentListFragment.KEY_DORMITORY_ID, dormitory.dormitoryId) }
                        )
                    }

                }
            }
        }
    }

}