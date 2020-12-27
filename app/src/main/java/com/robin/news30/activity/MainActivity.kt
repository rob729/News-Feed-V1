package com.robin.news30.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.robin.news30.R
import com.robin.news30.application.MyApplication
import com.robin.news30.databinding.ActivityMainBinding
import com.robin.news30.di.activity.ActivityModule
import com.techyourchance.dagger2course.screens.common.activities.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        supportActionBar?.elevation = 0f


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.home_container) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.home_container)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        binding.appBarLayout.setExpanded(true, true)
    }

    fun updateTittle(title: String){
        binding.titleToolbar.text = title
    }
}
