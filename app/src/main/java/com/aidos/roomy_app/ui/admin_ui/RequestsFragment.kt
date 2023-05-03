package com.aidos.roomy_app.ui.admin_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.aidos.roomy_app.R
import com.aidos.roomy_app.databinding.FragmentRequestsBinding
import com.aidos.roomy_app.enums.RequestStatus
import com.aidos.roomy_app.models.Dormitory
import com.aidos.roomy_app.ui.theme.RoomyMainTheme
import com.aidos.roomy_app.ui.ui_components.RequestItem
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class RequestsFragment : DaggerFragment() {
    companion object {
        const val KEY_ADMIN_DORMITORY = "KEY_ADMIN_DORMITORY"
    }

    fun dormitoryArgs() = requireArguments().getSerializable(KEY_ADMIN_DORMITORY) as? Dormitory

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var binding: FragmentRequestsBinding? = null

    private val viewModel: RequestsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[RequestsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRequestsBinding.inflate(inflater, container, false)
        val binding = binding ?: return null
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dormitoryArgs()?.let { viewModel.loadRequests(it.dormitoryId) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = binding ?: return

        binding.composeView.setContent {
            val requests by viewModel.requestsStateFlow.collectAsState()
            val message by viewModel.messageStateFlow.collectAsState()
            val requestTitle = stringResource(id = R.string.request_management)
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

            RoomyMainTheme {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        modifier = Modifier
                            .padding(25.dp),
                        text = AnnotatedString(requestTitle),
                        style = MaterialTheme.typography.h4.copy(
                            color = MaterialTheme.colors.onSurface
                        )
                    )

                    if (requests.isNotEmpty())
                        requests.forEach { request ->
                            RequestItem(request = request,
                                onAcceptClicked = {
                                    viewModel.setRequestStatus(request, RequestStatus.ACCEPTED)
                                },
                                onRejectClicked = {
                                    viewModel.setRequestStatus(request, RequestStatus.REJECTED)
                                }
                            )
                        }
                    else
                        Text(
                            modifier = Modifier
                                .padding(25.dp),
                            text = AnnotatedString(requestTitle),
                            style = MaterialTheme.typography.body2.copy(
                                color = MaterialTheme.colors.primaryVariant
                            )
                        )
                }
            }
        }
    }
}