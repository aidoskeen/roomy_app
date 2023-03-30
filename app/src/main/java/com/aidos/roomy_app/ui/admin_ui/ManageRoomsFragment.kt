package com.aidos.roomy_app.ui.admin_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.aidos.roomy_app.databinding.FragmentManageRoomsBinding
import com.aidos.roomy_app.ui.theme.RoomyMainTheme
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ManageRoomsFragment : DaggerFragment() {

    companion object {
        fun newInstance() = ManageRoomsFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var binding: FragmentManageRoomsBinding? = null
    private val viewModel: ManageRoomsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ManageRoomsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageRoomsBinding.inflate(inflater, container, false)
        val binding = binding ?: return null
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = binding ?: return

        binding.composeView.setContent {
            RoomyMainTheme {
                
            }
        }
    }
}