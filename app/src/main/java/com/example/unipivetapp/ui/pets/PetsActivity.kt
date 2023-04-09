package com.example.unipivetapp.ui.pets

import android.view.MenuItem
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.domain.models.Pet
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseActivity
import com.example.unipivetapp.databinding.ActivityPetsBinding
import com.example.unipivetapp.ui.pets.navigator.PetNavigatorImpl
import com.example.unipivetapp.util.PET
import com.example.unipivetapp.util.ext.getParcelable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PetsActivity : BaseActivity<ActivityPetsBinding>(R.layout.activity_pets) {

    private val viewModel: PetsViewModel by viewModels()

    private lateinit var navController: NavController
    private lateinit var navigator: PetNavigatorImpl

    override fun init() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navController = findNavController(R.id.nav_host_fragment)
        navigator = PetNavigatorImpl(this, navController)

        intent.getParcelable<Pet>(PET)?.let {
            viewModel.setSelectedPet(it)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            navigator.goBack()
            true
        }
        else -> false
    }

    override fun onBackPressed() {
        navigator.goBack()
        viewModel.resetNavigation()
    }
}