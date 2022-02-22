package com.example.githubsearch.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.tarun.myapplication.R
import com.tarun.myapplication.databinding.FragmentWebViewBinding


class WebViewFragment : Fragment() {
    private lateinit var binding: FragmentWebViewBinding
    private val args: WebViewFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_web_view, container, false)
        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables", "SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.WebView.webViewClient = WebViewClient()
        binding.WebView.apply {
            loadUrl(args.link)
            settings.javaScriptEnabled = true
        }


    }


}