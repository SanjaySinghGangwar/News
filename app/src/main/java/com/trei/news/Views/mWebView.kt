package com.trei.news.Views

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.trei.news.databinding.MwebViewBinding


class mWebView : Fragment() {

    private var _binding: MwebViewBinding? = null
    private val bind get() = _binding!!
    val args: mWebViewArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MwebViewBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAllComponents()
        initWebView()
        loadUrl(args.url)
    }

    private fun initAllComponents() {

    }

    private fun initWebView() {
        val webSettings: WebSettings = bind.webView.settings
        webSettings.javaScriptEnabled = true


        bind.webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return false
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (bind.progressBar.visibility == View.VISIBLE) {
                    bind.progressBar.visibility = View.GONE
                }
            }
        }
    }
    private fun loadUrl(url: String) {
        if (bind.progressBar.visibility == View.GONE) {
            bind.progressBar.visibility = View.VISIBLE
        }
        bind.webView.loadUrl(url)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}