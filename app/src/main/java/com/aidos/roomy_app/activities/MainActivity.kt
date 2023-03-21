package com.aidos.roomy_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.aidos.roomy_app.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var navController: NavController
    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {

        }
        NavigationBarView.OnItemReselectedListener {
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
                    navController.navigate(R.id.action_global_announcementsFragment)
                    true
                }
                R.id.profile -> {
                    navController.navigate(R.id.action_global_profileFragment)
                    true
                }
                else -> false
            }
        }
    }
}