package com.example.unipivetapp.ui.articles

import android.annotation.SuppressLint
import android.view.MenuItem
import com.example.domain.models.FeaturedItem
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseActivity
import com.example.unipivetapp.databinding.ActivityArticleBinding
import com.example.unipivetapp.util.ITEM
import com.example.unipivetapp.util.ext.getParcelable

class ArticleActivity : BaseActivity<ActivityArticleBinding>(R.layout.activity_article) {

    @SuppressLint("SetJavaScriptEnabled")
    override fun init() {
        title = getString(R.string.article_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent.getParcelable<FeaturedItem>(ITEM)?.let { featuredItem ->
            binding.webView.apply {
                loadUrl(featuredItem.url)
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
            }
        }
    }

    override fun startOps() {}
    override fun stopOps() {}

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            finish()
            true
        }
        else -> false
    }
}