package com.example.savemeal

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.savemeal.databinding.ActivityMainBinding
import com.example.savemeal.login.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    lateinit var userEmail: String
    var isShop: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userEmail = intent.extras?.getString("email").toString()
        isShop = intent.extras?.getBoolean("isShop") == true

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        if (isShop){
            navView.menu.clear() //clear old inflated items.
            navView.inflateMenu(R.menu.activity_main_drawer_shop)
            navController.setGraph(R.navigation.shop_navigation)
            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.activeDeliveriesFragment, R.id.shopProductsFragment
                ), drawerLayout
            )
        } else {
            navView.menu.clear() //clear old inflated items.
            navView.inflateMenu(R.menu.activity_main_drawer_consumer)
            navController.setGraph(R.navigation.consumer_navigation)
            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.availableMealsFragment, R.id.reservationsFragment
                ), drawerLayout
            )
        }
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.toString() == "Logout"){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish();
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}