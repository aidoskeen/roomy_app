package com.aidos.roomy_app.ui.admin_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModelProvider
import com.aidos.roomy_app.R
import com.aidos.roomy_app.databinding.FragmentEditRoomBinding
import com.aidos.roomy_app.enums.RoomSize
import com.aidos.roomy_app.models.Room
import com.aidos.roomy_app.ui.resident_ui.BookingFragment
import com.aidos.roomy_app.ui.theme.RoomyMainTheme
import com.aidos.roomy_app.ui.ui_components.RoomEditForm
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class EditRoomFragment : DaggerFragment() {

    companion object {
        const val ROOM = "ROOM"
    }
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: EditRoomViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[EditRoomViewModel::class.java]
    }
    var binding: FragmentEditRoomBinding? = null

    private fun roomArgs() = requireArguments().getSerializable(BookingFragment.ROOM) as? Room

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditRoomBinding.inflate(inflater, container, false)
        val binding = binding ?: return null
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = binding ?: return
        val roomSizes = listOf("BIG", "MEDIUM", "SMALL")
        val roomTypes = listOf("SINGLE", "DOUBLE", "TRIPLE")
        val roomToBeEdited = roomArgs()
        binding.composeView.setContent {
            RoomyMainTheme {
                if (roomToBeEdited != null) {
                    RoomEditForm(
                        room = roomToBeEdited,
                        roomSizesList = roomSizes,
                        roomTypesList = roomTypes,
                        onButtonClick = {
                            //TODO
                        }
                    )
                }
            }
        }
    }
}