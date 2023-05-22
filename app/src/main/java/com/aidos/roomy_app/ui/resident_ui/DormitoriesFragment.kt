package com.aidos.roomy_app.ui.resident_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aidos.roomy_app.R
import com.aidos.roomy_app.activities.MainViewModel
import com.aidos.roomy_app.databinding.FragmentDormitoriesBinding
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

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
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
        viewModel.loadDormitories()
        binding.dormsComposeView.setContent {
            val dormitoriesState by viewModel.dormitoriesStateFlow.collectAsState()
            RoomyMainTheme {
                Column {
                    RoomyTopAppBar(
                        backgroundColor = MaterialTheme.colors.primary,
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        label = "Roomy",
                        onUserIconClick = { }
                    )
                    val dormitories = dormitoriesState
                    dormitories.forEach { dormitory ->
                        val icon = if (dormitory.university.uppercase() == "VGTU")
                            painterResource(id = R.drawable.vgtu_image)
                            else painterResource(id = R.drawable.ic_dormitory)
                        DormitoryItemRow(
                            item = dormitory,
                            painter = icon,
                            label = stringResource(id = R.string.dorm_item_label),
                            onItemClicked = {
                                mainViewModel.setDormitory(dormitory)
                                findNavController().navigate(R.id.action_dormitoriesFragment_to_roomsFragment,
                                Bundle().apply
                                 {
                                     putSerializable(RoomsFragment.KEY_DORMITORY, dormitory)  }
                                )
                            })

                        Divider(
                            modifier = Modifier.padding(top = 12.dp, start = 12.dp, end = 12.dp),
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                }
            }
        }
    }



}