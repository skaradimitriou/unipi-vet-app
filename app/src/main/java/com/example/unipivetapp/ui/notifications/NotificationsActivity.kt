package com.example.unipivetapp.ui.notifications

import android.view.MenuItem
import androidx.activity.viewModels
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseActivity
import com.example.unipivetapp.databinding.ActivityNotificationsBinding
import com.example.unipivetapp.ui.notifications.adapter.NotificationsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationsActivity :
    BaseActivity<ActivityNotificationsBinding>(R.layout.activity_notifications) {

    private val viewModel: NotificationsViewModel by viewModels()
    private val adapter = NotificationsAdapter()

    override fun init() {
        title = getString(R.string.notifications_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.adapter = adapter
    }

    override fun startOps() {
        viewModel.getAllNotifications()

        viewModel.notifications.observe(this) { notifications ->
            binding.emptyResults = notifications.isEmpty()
            adapter.submitList(notifications)
            viewModel.readAllNotifications()
        }
    }

    override fun stopOps() {}

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            finish()
            false
        }
        else -> false
    }
}