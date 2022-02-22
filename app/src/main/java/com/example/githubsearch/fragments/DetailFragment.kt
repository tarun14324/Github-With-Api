package com.example.githubsearch.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.githubsearch.utilities.loadImage
import com.tarun.myapplication.R
import com.tarun.myapplication.databinding.FragmentDetailBinding

private lateinit var binding: FragmentDetailBinding

class DetailFragment : Fragment() {
    private val args: DetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.name = args.name
        binding.Name.text = args.name
        binding.RepoUrl.text = args.repoUrl
        binding.ImageView.loadImage(args.url)
        binding.LoadUrl.setOnClickListener {
            val action =
                DetailFragmentDirections.actionDetailFragmentToWebViewFragment(args.repoUrl)
            findNavController().navigate(action)
        }
    }
}