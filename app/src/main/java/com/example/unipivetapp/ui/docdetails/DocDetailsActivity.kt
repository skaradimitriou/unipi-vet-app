package com.example.unipivetapp.ui.docdetails

import android.view.MenuItem
import androidx.activity.viewModels
import com.example.domain.models.Vet
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseActivity
import com.example.unipivetapp.databinding.ActivityDocDetailsBinding
import com.example.unipivetapp.util.VET
import com.example.unipivetapp.util.ext.getParcelable

class DocDetailsActivity : BaseActivity<ActivityDocDetailsBinding>(R.layout.activity_doc_details) {

    private val viewModel: DocDetailsViewModel by viewModels()
    private val adapter = DocDetailsAdapter(object : DocDetailsCallback {
        override fun onReviewsClick() {
            //FIXME: Open reviews activity
        }

        override fun onAppointmentClick() {
            //FIXME: Open appointment activity or flow
        }
    })

    override fun init() {
        title = "Πληροφορίες"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.adapter = adapter
        intent.getParcelable<Vet>(VET)?.let {
            viewModel.displayVetDetails(it)
        }
    }

    override fun startOps() {
        viewModel.vetInfo.observe(this) { vetInfo ->
            adapter.submitList(vetInfo)
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