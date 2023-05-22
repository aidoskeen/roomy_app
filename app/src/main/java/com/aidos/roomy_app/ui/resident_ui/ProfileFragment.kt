package com.aidos.roomy_app.ui.resident_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.aidos.roomy_app.databinding.FragmentProfileBinding
import com.aidos.roomy_app.models.User
import com.aidos.roomy_app.ui.theme.RoomyMainTheme
import com.aidos.roomy_app.ui.ui_components.ProfileScreen
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {

    companion object {
        const val CURRENT_RESIDENT = "CURRENT_RESIDENT"
    }

    fun currentResidentArgs() = requireArguments().getSerializable(CURRENT_RESIDENT) as? User.Resident

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var binding: FragmentProfileBinding? = null
    private val viewModel: ProfileViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ProfileViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        val binding = binding ?: return null
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = binding ?: return
        val resident = viewModel.getCurrentResident()
        binding.composeView.setContent {
            RoomyMainTheme {
                ProfileScreen(user = resident)
            }
        }
    }
}