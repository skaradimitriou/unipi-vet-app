package com.example.unipivetapp.ui.onboarding

import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseActivity
import com.example.unipivetapp.databinding.ActivityMainBinding
import com.example.unipivetapp.ui.onboarding.navigator.OnboardingNavigatorImpl
import com.example.unipivetapp.util.hideActionBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: OnboardingSharedViewModel by viewModels()

    private lateinit var navController: NavController
    private lateinit var navigator: OnboardingNavigatorImpl

    override fun init() {
        hideActionBar()
        navController = findNavController(R.id.nav_host_fragment)
        navigator = OnboardingNavigatorImpl(this, navController)

        navController.addOnDestinationChangedListener { _, _, _ ->
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
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