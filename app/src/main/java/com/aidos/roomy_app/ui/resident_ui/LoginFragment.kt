package com.aidos.roomy_app.ui.resident_ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aidos.roomy_app.R
import com.aidos.roomy_app.activities.AdministrationActivity
import com.aidos.roomy_app.databinding.FragmentLoginBinding
import com.aidos.roomy_app.ui.theme.RoomyMainTheme
import com.aidos.roomy_app.ui.ui_components.InputField
import com.aidos.roomy_app.ui.ui_components.RoomyButton
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoginFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private var binding: FragmentLoginBinding? = null
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val binding = binding ?: return null
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = binding ?: return
        val bottomNavBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavBar.visibility = View.GONE
        viewModel.onLogin.observe(viewLifecycleOwner) { success ->
            if (success)
                findNavController().navigate(R.id.action_loginFragment_to_dormitoriesFragment)
            else
                Toast.makeText(requireContext(), "Login failed", Toast.LENGTH_SHORT).show()
        }

        binding.loginComposeView.setContent {
            val loginSuccessful by viewModel.loginSuccessful.collectAsState()

            RoomyMainTheme {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    var username by remember { mutableStateOf(TextFieldValue()) }
                    var password by remember { mutableStateOf(TextFieldValue()) }

                    Text(
                        text = stringResource(id = R.string.login_label),
                        style = MaterialTheme.typography.h2.copy(color = MaterialTheme.colors.primary)
                    )
                    //Username input
                    InputField(
                        label = { Text(text = stringResource(id = R.string.username)) },
                        value = username,
                        onValueChange = { username = it })

                    //Password input
                    InputField(
                        label = { Text(text = stringResource(id = R.string.password)) },
                        value = password,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        onValueChange = { password = it })

                    Spacer(modifier = Modifier.height(20.dp))

                    //Button for signing in
                    RoomyButton(
                        onClick = { viewModel.logIn(username.text, password.text) },
                        text = stringResource(id = R.string.login_button)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    //Button for registration
                    RoomyButton(
                        text = stringResource(id = R.string.registration),
                        onClick = { findNavController().navigate(R.id.action_loginFragment_to_registrationFragment) },
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    RoomyButton(
                        color = MaterialTheme.colors.primary,
                        text = stringResource(id = R.string.admin_login_button),
                        onClick = {
                            startAdminActivity()
                        }
                    )
                }
            }
        }
    }

    private fun startAdminActivity() {
        startActivity(Intent(requireActivity(), AdministrationActivity::class.java));
    }

}