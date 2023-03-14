package com.aidos.roomy_app.ui.resident_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.aidos.roomy_app.R
import com.aidos.roomy_app.databinding.FragmentRoomsBinding
import com.aidos.roomy_app.models.Dormitory
import com.aidos.roomy_app.ui.theme.RoomyMainTheme
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class RoomsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var binding: FragmentRoomsBinding? = null

    companion object {
        const val DORMITORY = "DORMITORY"
    }

    private fun dormitoryArgs() = requireArguments().getSerializable(DORMITORY) as? Dormitory

    private val viewModel: RoomsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[RoomsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoomsBinding.inflate(inflater, container, false)
        val binding = binding ?: return null
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = binding ?: return

        binding.roomsComposeView.setContent {
            RoomyMainTheme {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colors.background)
                        .verticalScroll(rememberScrollState()),
                ) {
                    Text(
                        modifier = Modifier
                            .padding(25.dp),
                        text = AnnotatedString(stringResource(id = R.string.dorm_item_label)),
                        style = MaterialTheme.typography.h3.copy(
                            color = MaterialTheme.colors.onSurface
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}