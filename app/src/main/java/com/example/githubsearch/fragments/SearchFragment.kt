package com.example.githubsearch.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.githubsearch.adapter.UserAdapter
import com.example.githubsearch.dataclass.Item
import com.example.githubsearch.utilities.BindingAdapterUtils.toast
import com.example.githubsearch.utilities.BindingAdapterUtils.updateVisibility
import com.example.githubsearch.utilities.ConnectivityStatus
import com.example.githubsearch.utilities.TextTitle
import com.example.githubsearch.utilities.hideKeyboard
import com.example.githubsearch.viewModel.ModelFactory
import com.example.githubsearch.viewModel.MyViewModel
import com.tarun.myapplication.R
import com.tarun.myapplication.databinding.FragmentSearchBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


private val TAG by lazy { "SearchFragment" }

class SearchFragment : Fragment(), LifecycleOwner {

    private lateinit var binding: FragmentSearchBinding

    private val viewModel: MyViewModel by lazy {
        ViewModelProvider(
            this,
            ModelFactory(requireActivity().application)
        )[MyViewModel::class.java]
    }

    private val adapter: UserAdapter by lazy { UserAdapter(this::onClick) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkConnectivity()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            Handler(Looper.getMainLooper()).postDelayed({
                binding.swipeRefreshLayout.isRefreshing = false
            }, 3000)
            adapter.refresh()
        }
        viewModel.itemsUpdated.observe(viewLifecycleOwner) {
            Log.e(TAG, "onViewCreated: $it")
            adapter.notifyDataSetChanged()
            adapter.submitData(lifecycle, it)
            Handler().postDelayed({
                updateVisibility(binding.progressBar, false)
            }, 2000)
        }
        binding.input.setOnEditorActionListener { v, actionId, event ->
            updateVisibility(binding.progressBar, true)
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEND -> {
                    v.hideKeyboard(requireContext())

                    true
                }
                else -> false
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        binding.model = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun connectionLost() {
        binding.recyclerView.adapter = adapter
        val myText: TextTitle = TextTitle.Builder()
            .Text(resources.getString(R.string.offline))
            .build()
        toast(
            requireView(), myText.text
        )
        lifecycleScope.launch {
            viewModel.getAllRoomData(binding.input.text.toString()).collectLatest {
                adapter.notifyDataSetChanged()
                adapter.submitData(lifecycle, it)
            }
        }
    }

    private fun onClick(item: Item) {
        val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(
            item.avatar_url!!,
            item.login!!,
            item.html_url!!
        )
        findNavController().navigate(action)
    }


    private fun checkConnectivity() {
        val connectivity = ConnectivityStatus(requireContext())
        connectivity.observe(this) { isConnected ->
            if (!isConnected) {
                connectionLost()
            }
        }
    }

}