package com.aidos.roomy_app.ui.resident_ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import com.aidos.roomy_app.databinding.FragmentRegistrationBinding
import com.aidos.roomy_app.models.User
import com.aidos.roomy_app.ui.theme.RoomyMainTheme
import com.aidos.roomy_app.ui.ui_components.InputField
import com.aidos.roomy_app.ui.ui_components.RoomyButton
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class RegistrationFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var binding: FragmentRegistrationBinding? = null
    private val viewModel: RegistrationViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[RegistrationViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        val binding = binding ?: return null
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onRegistrationResult.observe(viewLifecycleOwner) { success ->
            if (success)  Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
            else Toast.makeText(requireContext(), "Registration failed", Toast.LENGTH_SHORT).show()
        }

        viewModel.onSuccess.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }

        binding?.regComposeView?.setContent {
            RoomyMainTheme {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .verticalScroll(
                            rememberScrollState()
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    val username = remember { mutableStateOf(TextFieldValue()) }
                    val password = remember { mutableStateOf(TextFieldValue()) }
                    val repeatedPassword = remember { mutableStateOf(TextFieldValue()) }
                    val name = remember { mutableStateOf(TextFieldValue()) }
                    val surname = remember { mutableStateOf(TextFieldValue()) }

                    Text(
                        text = stringResource(id = R.string.registration),
                        style = MaterialTheme.typography.h2.copy(color = MaterialTheme.colors.primary)
                    )
                    //Name
                    InputField(
                        label = { Text(text = stringResource(id = R.string.firstname)) },
                        value = name.value,
                        onValueChange = { name.value = it })

                    InputField(
                        label = { Text(text = stringResource(id = R.string.surname)) },
                        value = surname.value,
                        onValueChange = { surname.value = it })
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
                    //Repeat password
                    InputField(
                        label = { Text(text = stringResource(id = R.string.repeat_password)) },
                        value = repeatedPassword.value,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        onValueChange = { repeatedPassword.value = it })


                    Spacer(modifier = Modifier.height(20.dp))
                    val user = User.Resident(
                        name = name.value.text,
                        surname = surname.value.text,
                        username = username.value.text,
                        password = username.value.text,
                    )

                    //Button for registration
                    RoomyButton(
                        onClick = { viewModel.createUser(user) },
                        text = stringResource(id = R.string.registration)
                    )

                }
            }
        }
    }

}