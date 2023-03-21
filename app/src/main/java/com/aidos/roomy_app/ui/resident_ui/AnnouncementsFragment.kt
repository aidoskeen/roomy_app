package com.aidos.roomy_app.ui.resident_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.aidos.roomy_app.databinding.FragmentAnnouncementsBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class AnnouncementsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: AnnouncementsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[AnnouncementsViewModel::class.java]
    }
    private var binding: FragmentAnnouncementsBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnnouncementsBinding.inflate(inflater, container, false)
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
    companion object {

    }
}