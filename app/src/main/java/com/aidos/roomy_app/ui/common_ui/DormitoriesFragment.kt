package com.aidos.roomy_app.ui.common_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModelProvider
import com.aidos.roomy_app.R
import com.aidos.roomy_app.databinding.FragmentDormitoriesBinding
import com.aidos.roomy_app.ui.theme.RoomyMainTheme
import com.aidos.roomy_app.ui.ui_components.RoomyTopAppBar
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = binding ?: return

        binding.dormsComposeView.setContent {
            RoomyMainTheme {
                RoomyTopAppBar(
                    backgroundColor = MaterialTheme.colors.primary,
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    label = "Roomy",
                    onUserIconClick = { }
                )
            }
        }
    }



}