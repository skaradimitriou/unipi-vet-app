package com.example.unipivetapp.ui.prehome

import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseActivity
import com.example.unipivetapp.databinding.ActivityPreHomeBinding
import com.example.unipivetapp.ui.prehome.navigator.PreHomeNavigatorImpl

class PreHomeActivity : BaseActivity<ActivityPreHomeBinding>(R.layout.activity_pre_home) {

    private val viewModel: PreHomeViewModel by viewModels()

    private lateinit var navController: NavController
    private lateinit var navigator: PreHomeNavigatorImpl

    override fun init() {
        navController = findNavController(R.id.nav_host_fragment)
        navigator = PreHomeNavigatorImpl(this, navController)
    }

    override fun startOps() {
        viewModel.navigatorState.observe(this) { action ->
            action?.let { navigator.navigateTo(it) }
        }
    }

    override fun stopOps() {
        viewModel.navigatorState.removeObservers(this)
    }

    override fun onBackPressed() {
        navigator.goBack()
        viewModel.resetNavigation()
    }
}