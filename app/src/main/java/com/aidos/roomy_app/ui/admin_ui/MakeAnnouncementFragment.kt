package com.aidos.roomy_app.ui.admin_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import com.aidos.roomy_app.databinding.FragmentMakeAnnouncementBinding
import com.aidos.roomy_app.ui.theme.RoomyMainTheme
import com.aidos.roomy_app.ui.ui_components.MakeAnnouncementForm
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MakeAnnouncementFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var binding: FragmentMakeAnnouncementBinding? = null

    private val viewModel: MakeAnnouncementViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MakeAnnouncementViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMakeAnnouncementBinding.inflate(inflater, container, false)
        val binding = binding ?: return null
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = binding ?: return


        binding.composeView.setContent {
            var text by  remember { mutableStateOf("") }
            var titleText by  remember { mutableStateOf("") }
            RoomyMainTheme {
                MakeAnnouncementForm(
                    text = text,
                    titleText = titleText,
                    onValueChange = { text = it },
                    onButtonClick = { },
                    onTitleValueChange = { titleText = it }
                )
            }
        }
    }


}
