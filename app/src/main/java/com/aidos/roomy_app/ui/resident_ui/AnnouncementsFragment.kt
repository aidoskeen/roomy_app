package com.aidos.roomy_app.ui.resident_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.aidos.roomy_app.R
import com.aidos.roomy_app.databinding.FragmentAnnouncementsBinding
import com.aidos.roomy_app.models.Dormitory
import com.aidos.roomy_app.models.Room
import com.aidos.roomy_app.ui.ui_components.AnnouncementRow
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class AnnouncementsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: AnnouncementsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[AnnouncementsViewModel::class.java]
    }
    private var binding: FragmentAnnouncementsBinding? = null

    private fun dormitoryArgs() = requireArguments().getSerializable(KEY_DORMITORY) as? Dormitory

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
        val dormitory = dormitoryArgs()

        binding.composeView.setContent {
                Column(
                    modifier = Modifier
                        .scrollable(rememberScrollState(), orientation = Orientation.Vertical)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.nothing_to_show),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        style = MaterialTheme.typography.h4,
                        color = MaterialTheme.colors.onSurface
                    )

                    if (dormitory != null && dormitory.announcements.isNotEmpty()) {
                        dormitory.announcements.forEach { announcement ->
                            AnnouncementRow(announcement = announcement)
                        }
                    }
                    else
                        Text(
                            text = stringResource(id = R.string.nothing_to_show),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 50.dp),
                            style = MaterialTheme.typography.body2.copy(
                                color = MaterialTheme.colors.onSurface
                            )
                        )
                }
        }
    }
    companion object {
        const val KEY_DORMITORY = "KEY_DORMITORY"
    }
}