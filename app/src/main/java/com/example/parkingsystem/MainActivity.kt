package com.example.parkingsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.parkingsystem.databinding.ActivityMainBinding

// TODO: create authentication activity - done
// TODO: add Navigation Library in order to navigate between fragments - done
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    // Delegate property activity binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        window?.requestFeature(Window.FEATURE_ACTION_BAR)
//        window?.requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY)
//        window?.requestFeature(Window.FEATURE_ACTION_MODE_OVERLAY)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}