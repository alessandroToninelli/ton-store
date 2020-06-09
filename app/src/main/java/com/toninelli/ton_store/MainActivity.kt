package com.toninelli.ton_store

import android.os.Bundle
import android.view.Menu
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.appcompat.app.AppCompatActivity
import com.toninelli.ton_store.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()

    }

    private fun setUpView() {

        val navController = findNavController(R.id.main_nav_host_fragment)

        val topLevelDestination = setOf(
            R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)

        val appBarConfiguration = AppBarConfiguration(topLevelDestination, drawer_layout)

        toolbar.apply {
            setSupportActionBar(this)
            this.setupWithNavController(navController, appBarConfiguration)
        }

        nav_view.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

}
