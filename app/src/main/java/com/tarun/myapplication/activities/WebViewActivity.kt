package com.tarun.myapplication.activities

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.tarun.myapplication.R
import com.tarun.myapplication.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_web_view)
        mBinding.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url!!)
                return true
            }
        }
        mBinding.webView.loadUrl(intent.getStringExtra("WebLink").toString())
        mBinding.toolBarBack.ivMenu.setOnClickListener {
            onBackPressed()
        }
        mBinding.toolBarBack.tvToolBarTitle.text = resources.getString(R.string.webView)

    }
    override fun onBackPressed() {
        if (mBinding.webView.canGoBack()) {
            mBinding.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

}