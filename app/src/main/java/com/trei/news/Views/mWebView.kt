package com.trei.news.Views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.trei.news.databinding.MwebViewBinding


class mWebView : Fragment() {

    private var _binding: MwebViewBinding? = null
    private val bind get() = _binding!!
    private lateinit var url: String
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
    }

    private fun initAllComponents() {
        url = args.url
        bind.webView.settings.javaScriptEnabled = true
        bind.webView.loadUrl(url)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}