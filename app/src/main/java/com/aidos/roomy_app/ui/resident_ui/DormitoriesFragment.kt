package com.aidos.roomy_app.ui.resident_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aidos.roomy_app.R
import com.aidos.roomy_app.databinding.FragmentDormitoriesBinding
import com.aidos.roomy_app.enums.RoomSize
import com.aidos.roomy_app.enums.RoomType
import com.aidos.roomy_app.models.Dormitory
import com.aidos.roomy_app.models.Room
import com.aidos.roomy_app.ui.theme.RoomyMainTheme
import com.aidos.roomy_app.ui.ui_components.DormitoryItemRow
import com.aidos.roomy_app.ui.ui_components.RoomyTopAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class DormitoriesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var binding: FragmentDormitoriesBinding? = null
    private val viewModel: DormitoriesViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[DormitoriesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDormitoriesBinding.inflate(inflater, container, false)
        val binding = binding ?: return null
        binding.lifecycleOwner = viewLifecycleOwner
        val bottomNavBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavBar.visibility = View.VISIBLE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = binding ?: return
        val room1 = Room(1, RoomType.DOUBLE, RoomSize.SMALL, listOf(), "Regular room")
        val room2 = Room(2, RoomType.SINGLE, RoomSize.MEDIUM, listOf(), "Regular room")
        val room3 = Room(3, RoomType.TRIPLE, RoomSize.BIG, listOf(), "Regular room")
        val dormList = listOf(
            Dormitory(
                dormitoryId = 11,
                address = "Sauletekio 25",
                roomQuantity = 100,
                rooms = listOf(room1, room2, room3),
                "VGTU"
            ),
            Dormitory(
                dormitoryId = 11,
                address = "Sauletekio 25",
                roomQuantity = 100,
                rooms = listOf(),
                "VGTU"
            ),
            Dormitory(
                dormitoryId = 11,
                address = "Sauletekio 25",
                roomQuantity = 100,
                rooms = listOf(),
                "VGTU"
            )
        )
        binding.dormsComposeView.setContent {
            RoomyMainTheme {
                Column {
                    RoomyTopAppBar(
                        backgroundColor = MaterialTheme.colors.primary,
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        label = "Roomy",
                        onUserIconClick = { }
                    )

                    dormList.forEach { dormitory ->
                        DormitoryItemRow(
                            item = dormitory,
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            label = stringResource(id = R.string.dorm_item_label),
                            onItemClicked = {
                                findNavController().navigate(R.id.action_dormitoriesFragment_to_roomsFragment,
                                Bundle().apply
                                 {
                                     putSerializable(RoomsFragment.DORMITORY, dormitory)  }
                                )
                            })
                    }
                }
            }
        }
    }



}