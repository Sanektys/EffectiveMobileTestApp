package com.example.effectivemobiletestapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.effectivemobiletestapp.R
import com.example.effectivemobiletestapp.databinding.ActivityMainBinding
import com.example.effectivemobiletestapp.ui.screens.search.SearchModalBottomSheet
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

        navView.setupWithNavController(navController)
    }

    fun showSearchDialog(fragmentManager: FragmentManager, bundle: Bundle) {
        if (fragmentManager.findFragmentByTag(SearchModalBottomSheet.TAG) != null) return

        SearchModalBottomSheet().let { dialog ->
            dialog.arguments = bundle
            dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetDialogTheme)
            dialog.show(fragmentManager, SearchModalBottomSheet.TAG)
        }
    }

    fun openSelectedCountryScreen(bundle: Bundle) {
        navController.navigate(R.id.action_navigation_home_to_selectedCountryFragment, bundle)
    }

    fun openAllTicketsScreen(bundle: Bundle) {
        navController.navigate(R.id.action_selectedCountryFragment_to_allTicketsListFragment, bundle)
    }

    fun openEmptyPlaceholderScreen() {
        navController.navigate(R.id.placeholder)
    }

    fun popBackStack() {
        navController.popBackStack()
    }
}