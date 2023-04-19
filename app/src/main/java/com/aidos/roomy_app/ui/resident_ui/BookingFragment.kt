package com.aidos.roomy_app.ui.resident_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aidos.roomy_app.R
import com.aidos.roomy_app.databinding.FragmentBookingBinding
import com.aidos.roomy_app.databinding.FragmentLoginBinding
import com.aidos.roomy_app.models.Room
import com.aidos.roomy_app.models.User
import com.aidos.roomy_app.ui.theme.RoomyMainTheme
import com.aidos.roomy_app.ui.ui_components.RoomBookingForm
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

class BookingFragment : Fragment() {

    companion object {
        const val ROOM = "ROOM"
        const val RESIDENT = "RESIDENT"
    }

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
            RoomyMainTheme() {
                if (resident != null) {
                    if (room != null)
                        RoomBookingForm(
                            resident = resident,
                            room = room,
                            date = currentDate
                        ) {

                        }
                }
                else
                    Text(text = stringResource(id = R.string.user_not_authorized))
            }
        }
    }
}