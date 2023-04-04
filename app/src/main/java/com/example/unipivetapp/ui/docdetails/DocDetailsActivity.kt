package com.example.unipivetapp.ui.docdetails

import android.content.Intent
import android.view.MenuItem
import androidx.activity.viewModels
import com.example.domain.models.Vet
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseActivity
import com.example.unipivetapp.databinding.ActivityDocDetailsBinding
import com.example.unipivetapp.ui.appointments.AppointmentsActivity
import com.example.unipivetapp.ui.ratings.RatingsActivity
import com.example.unipivetapp.util.DOC_ID
import com.example.unipivetapp.util.VET
import com.example.unipivetapp.util.ext.getParcelable

class DocDetailsActivity : BaseActivity<ActivityDocDetailsBinding>(R.layout.activity_doc_details) {

    private val viewModel: DocDetailsViewModel by viewModels()
    private val adapter = DocDetailsAdapter(object : DocDetailsCallback {
        override fun onReviewsClick(docId: Int) {
            startActivity(Intent(this@DocDetailsActivity, RatingsActivity::class.java).apply {
                putExtra(DOC_ID, docId)
            })
        }

        override fun onAppointmentClick() {
            startActivity(Intent(this@DocDetailsActivity, AppointmentsActivity::class.java).apply {
                putExtra(VET, viewModel.getVetInfo())
            })
        }
    })

    override fun init() {
        title = getString(R.string.vet_details_title)
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