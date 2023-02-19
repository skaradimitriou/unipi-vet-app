package com.example.unipivetapp.ui.dashboard

import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseActivity
import com.example.unipivetapp.databinding.ActivityDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity<ActivityDashboardBinding>(R.layout.activity_dashboard) {

    private lateinit var navController: NavController

    override fun init() {
        navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavigationMenu.setupWithNavController(navController)
    }

    override fun startOps() {}

    override fun stopOps() {}
}