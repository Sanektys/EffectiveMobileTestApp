package com.example.effectivemobiletestapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.effectivemobiletestapp.R
import com.example.effectivemobiletestapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNavigationBar

        val navHost = supportFragmentManager
            .findFragmentById(R.id.activity_main_navigation_fragment) as NavHostFragment
        navController = navHost.navController

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_hotels, R.id.navigation_shortly,
//                R.id.navigation_subscriptions, R.id.navigation_profile
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    fun openSelectedCountryScreen() {
        navController.navigate(R.id.action_navigation_home_to_selectedCountryFragment)
    }

    fun openAllTicketsScreen() {
        navController.navigate(R.id.action_selectedCountryFragment_to_allTicketsListFragment)
    }

    fun popBackStack() {
        navController.popBackStack()
    }
}