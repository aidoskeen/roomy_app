package com.aidos.roomy_app.ui.admin_ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aidos.roomy_app.R
import com.aidos.roomy_app.activities.AdministrationActivity
import com.aidos.roomy_app.activities.MainActivity
import com.aidos.roomy_app.databinding.FragmentAnnouncementBinding
import com.aidos.roomy_app.ui.theme.RoomyMainTheme
import com.aidos.roomy_app.ui.ui_components.InputField
import com.aidos.roomy_app.ui.ui_components.RoomyButton
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AnnouncementFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var binding: FragmentAnnouncementBinding? = null

    private val viewModel: AnnouncementViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[AnnouncementViewModel::class.java]
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
            RoomyMainTheme {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    val username = remember { mutableStateOf(TextFieldValue()) }
                    val password = remember { mutableStateOf(TextFieldValue()) }

                    Text(
                        text = stringResource(id = R.string.login_label),
                        style = MaterialTheme.typography.h2.copy(color = MaterialTheme.colors.primary)
                    )
                    //Username input
                    InputField(
                        label = { Text(text = stringResource(id = R.string.username)) },
                        value = username.value,
                        onValueChange = { username.value = it })

                    //Password input
                    InputField(
                        label = { Text(text = stringResource(id = R.string.password)) },
                        value = password.value,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        onValueChange = { password.value = it })

                    Spacer(modifier = Modifier.height(20.dp))

                    //Button for signing in
                    RoomyButton(
                        onClick = {
                            startAdminActivity()
                        },
                        text = stringResource(id = R.string.login_button)
                    )
                }
            }
        }
    }

    private fun startAdminActivity() {
        startActivity(Intent(requireContext(), AdministrationActivity::class.java));
    }
}
