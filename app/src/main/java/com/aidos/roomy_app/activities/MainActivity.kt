package com.aidos.roomy_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.aidos.roomy_app.R
import com.aidos.roomy_app.ui.resident_ui.AnnouncementsFragment
import com.aidos.roomy_app.ui.resident_ui.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val navigationView = findViewById<NavigationBarView>(R.id.bottom_navigation)
        navigationView.setOnItemSelectedListener {
                item ->
            when(item.itemId) {
                R.id.home -> {
                    navController.navigate(R.id.action_global_dormitoriesFragment)
                    true
                }
                R.id.my_room -> {
                    navController.navigate(R.id.action_global_myRoomFragment)
                    true
                }
                R.id.news -> {
                    navController.navigate(R.id.action_global_announcementsFragment,
                    Bundle().apply
                     {
                         putSerializable(AnnouncementsFragment.KEY_DORMITORY, viewModel.dormitoryFlow.value)
                     })
                    true
                }
                R.id.profile -> {
                    navController.navigate(
                        R.id.action_global_profileFragment,
                        Bundle().apply {
                            putSerializable(ProfileFragment.CURRENT_RESIDENT, viewModel.getCurrentResident())
                        })
                    true
                }
                else -> false
            }
        }
        if (savedInstanceState == null) {

        }

    }
}