package com.example.unipivetapp.ui.articles

import android.annotation.SuppressLint
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.domain.models.FeaturedItem
import com.example.unipivetapp.R
import com.example.unipivetapp.base.BaseActivity
import com.example.unipivetapp.databinding.ActivityArticleBinding
import com.example.unipivetapp.util.ITEM
import com.example.unipivetapp.util.ext.getParcelable

class ArticleActivity : BaseActivity<ActivityArticleBinding>(R.layout.activity_article) {
    
    override fun init() {
        title = getString(R.string.article_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent.getParcelable<FeaturedItem>(ITEM)?.let { featuredItem ->
            binding.isLoading = true
            loadItem(featuredItem)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadItem(featuredItem: FeaturedItem) {
        binding.webView.apply {
            loadUrl(featuredItem.url)
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding.isLoading = false
                }
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