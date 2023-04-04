package com.example.unipivetapp.ui.ratings

import android.view.MenuItem
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseActivity
import com.example.unipivetapp.databinding.ActivityRatingsBinding
import com.example.unipivetapp.ui.ratings.navigator.RatingsNavigatorImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RatingsActivity : BaseActivity<ActivityRatingsBinding>(R.layout.activity_ratings) {

    private val viewModel: RatingsViewModel by viewModels()

    private lateinit var navController: NavController
    private lateinit var navigator: RatingsNavigatorImpl

    override fun init() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navController = findNavController(R.id.nav_host_fragment)
        navigator = RatingsNavigatorImpl(this, navController)
    }

    override fun startOps() {
        viewModel.navigatorState.observe(this) { mode ->
            mode?.let { navigator.navigateTo(it) }
        }
    }

    override fun stopOps() {
        viewModel.navigatorState.removeObservers(this)
    }

    override fun onBackPressed() {
        navigator.goBack()
        viewModel.resetNavigation()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            finish()
            false
        }
        else -> false
    }
}