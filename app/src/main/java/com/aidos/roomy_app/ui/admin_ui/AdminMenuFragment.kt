package com.aidos.roomy_app.ui.admin_ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aidos.roomy_app.R
import com.aidos.roomy_app.databinding.FragmentAdminLoginBinding
import com.aidos.roomy_app.databinding.FragmentAdminMenuBinding
import com.aidos.roomy_app.ui.resident_ui.LoginViewModel
import com.aidos.roomy_app.ui.theme.RoomyMainTheme
import com.aidos.roomy_app.ui.ui_components.MenuOption
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AdminMenuFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var binding: FragmentAdminMenuBinding? = null
    private val viewModel: AdminMenuViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[AdminMenuViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdminMenuBinding.inflate(inflater, container, false)
        val binding = binding ?: return null
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = binding ?: return
        
        binding.composeView.setContent { 
            RoomyMainTheme {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .padding(25.dp),
                        text = AnnotatedString(stringResource(id = R.string.administration_menu_title)),
                        style = MaterialTheme.typography.h4.copy(
                            color = MaterialTheme.colors.onSurface
                        ),
                        textAlign = TextAlign.Center
                    )
                    val dividerColor = MaterialTheme.colors.onSurface
                    MenuOption(
                        text = stringResource(id = R.string.make_announcement),
                        onClick = { }
                    )

                    Divider(color = dividerColor)

                    MenuOption(
                        text = stringResource(id = R.string.check_requests),
                        onClick = { }
                    )

                    Divider(color = dividerColor)

                    MenuOption(
                        text = stringResource(id = R.string.check_requests),
                        onClick = { }
                    )

                    Divider(color = dividerColor)

                }
            }
        }
    }

}