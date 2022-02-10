package com.tarun.myapplication.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tarun.myapplication.utiles.CheckNetworkConnection
import com.tarun.myapplication.R
import com.tarun.myapplication.adapter.RoomAdapter
import com.tarun.myapplication.adapter.UserAdapter
import com.tarun.myapplication.databinding.ActivityMainBinding
import com.tarun.myapplication.dataclass.Item
import com.tarun.myapplication.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class MainActivity : AppCompatActivity() {
    val TAG = "github"
    private lateinit var mBinding: ActivityMainBinding

    private val viewModel: MainViewModel by ViewModelLazy(MainViewModel::class,
        { viewModelStore },
        { defaultViewModelProviderFactory })

    private val checkNetworkConnection by lazy { CheckNetworkConnection(application) }

    private var userAdapter = UserAdapter((::dbItemClicked))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initRecyclerView()

    }

    override fun onResume() {
        super.onResume()
        callNetworkConnection()
    }

    private fun callNetworkConnection() {
        checkNetworkConnection.observe(this) { isConnected ->
            if (isConnected) {
                mBinding.input.setOnEditorActionListener { p_0, p_1, p_2 ->
                    if (mBinding.input.text.toString().isEmpty()) {
                        Toast.makeText(
                            this,
                            "input can't be empty",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        submit(mBinding.input.text.toString())
                    }
                    true
                }
            } else {
                lifecycleScope.launch {
                    viewModel.getDataBAseData().collectLatest { pagedData ->
                        val adapter = RoomAdapter()
                        adapter.submitData(pagedData)
                    }
                }
            }
        }

    }

    private fun submit(query: String) {
        lifecycleScope.launch {
            refreshData(query)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun refreshData(query: String) {
        lifecycleScope.launch {
            viewModel.pagedData(query).observe(this@MainActivity) {
                userAdapter.submitData(lifecycle, it)
                mBinding.recyclerView.adapter = userAdapter
            }
        }

    }

    private fun initRecyclerView() {
        mBinding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            smoothScrollToPosition(0)
        }
    }

    private fun dbItemClicked(item: Item) {
        val int = Intent(this, DetailActivity::class.java)
        int.putExtra("Profile", item.avatar_url)
        int.putExtra("WebLink", item.html_url)
        int.putExtra("Name", item.login)
        startActivity(int)
    }
}