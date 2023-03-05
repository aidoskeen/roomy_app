package com.aidos.roomy_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.aidos.roomy_app.R
import dagger.android.AndroidInjection
import javax.inject.Inject

class AdministrationActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val adminViewModel: AdministrationViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[AdministrationViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administration)
    }
}