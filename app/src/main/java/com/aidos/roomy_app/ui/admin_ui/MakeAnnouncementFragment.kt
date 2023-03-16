package com.aidos.roomy_app.ui.admin_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.aidos.roomy_app.databinding.FragmentAnnouncementBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MakeAnnouncementFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var binding: FragmentAnnouncementBinding? = null

    private val viewModel: MakeAnnouncementViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MakeAnnouncementViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnnouncementBinding.inflate(inflater, container, false)
        val binding = binding ?: return null
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = binding ?: return


        binding.composeView.setContent {

        }
    }


}
