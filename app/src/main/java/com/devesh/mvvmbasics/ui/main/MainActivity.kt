package com.devesh.mvvmbasics.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.devesh.mvvmbasics.MvvmBasics
import com.devesh.mvvmbasics.R
import com.devesh.mvvmbasics.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    val mainComponent by lazy(LazyThreadSafetyMode.NONE) {
        (application as MvvmBasics).appComponent.mainComponent().create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = MainActivityBinding.inflate(layoutInflater)

        setSupportActionBar(binding.toolbar)

        val navHost: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostfragment) as NavHostFragment

        val navController = navHost.navController

        setupActionBarWithNavController(navController)

        setContentView(binding.root)
    }
}