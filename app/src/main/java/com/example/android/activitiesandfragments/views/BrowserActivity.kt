package com.example.android.activitiesandfragments.views

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.android.R
import com.example.android.activitiesandfragments.Constants.Keys.RESULT_KEY_SECOND_ACTIVITY
import com.example.android.databinding.ActivityBrowserBinding

class BrowserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBrowserBinding
    private lateinit var strHttps: String
    private lateinit var strHttp: String
    private lateinit var wwwDot: String
    private lateinit var strLandingPage: String
    private lateinit var messageToSendBack: String

    private val webViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            val url = binding.etSearchBar.text.toString()
            if (url.isNotEmpty() && (url.startsWith(strHttps)) || url.startsWith(strHttp)) {
                view?.loadUrl(url)
            }
            return false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBrowserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getStrings()
        setUpOnClickListeners()
        setUpEditText()
        setUpOnBackPressed()
        setUpWebView()
    }

    private fun getStrings() {
        strHttps = getString(R.string.https)
        strHttp = getString(R.string.http)
        wwwDot = getString(R.string.www_dot)
        strLandingPage = getString(R.string.default_landing_page)
        messageToSendBack = getString(R.string.message_to_send)
    }

    private fun setUpEditText() {
        binding.etSearchBar.setSelectAllOnFocus(true)
        binding.etSearchBar.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loadUrl()
            }
            true
        }
    }

    private fun loadUrl() {
        val urlString = binding.etSearchBar.text
        val url =
            if (urlString != null &&
                urlString.toString().isNotEmpty()
            ) validateAndGetUrl() else strLandingPage
        binding.webView.loadUrl(url)
    }

    private fun validateAndGetUrl(): String {
        var urlInput = binding.etSearchBar.text.toString()
        if (!urlInput.startsWith(wwwDot) && !urlInput.startsWith(strHttps)) {
            urlInput = wwwDot + urlInput
        }
        if (!urlInput.startsWith(strHttps)) {
            urlInput = strHttps + urlInput
        }
        return urlInput
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setUpWebView() {
        binding.webView.webViewClient = webViewClient
        binding.webView.settings.javaScriptEnabled = true
        binding.etSearchBar.setText(strLandingPage)
        loadUrl()
    }

    private fun setUpOnClickListeners() {
        binding.btnSearch.setOnClickListener {
            val url = binding.etSearchBar.text.toString()
            if (url.isNotEmpty() && (url.startsWith(strHttps) || url.startsWith(
                    strHttp
                ))
            ) {
                binding.webView.loadUrl(url)
            }
        }
    }

    private fun setUpOnBackPressed() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.webView.canGoBack()) {
                    binding.webView.goBack()
                } else {
                    finish()
                }
            }
        })
    }

    override fun finish() {
        val data = Intent()
        data.putExtra(RESULT_KEY_SECOND_ACTIVITY, messageToSendBack)
        setResult(RESULT_OK, data)
        super.finish()
    }

}