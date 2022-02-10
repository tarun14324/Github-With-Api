package com.tarun.myapplication.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.tarun.myapplication.R
import com.tarun.myapplication.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        mBinding.link.text = intent.getStringExtra("WebLink")
        mBinding.name.text = intent.getStringExtra("Name")

        Glide.with(this)
            .load(intent.getStringExtra("Profile"))
            .into(mBinding.userImageHeader)
        mBinding.link.setOnClickListener {
            val int = Intent(this, WebViewActivity::class.java)
            int.putExtra("WebLink", intent.getStringExtra("WebLink"))
            startActivity(int)
        }
        mBinding.toolBarBack.tvToolBarTitle.text=resources.getString(R.string.detail)
        mBinding.toolBarBack.ivMenu.setOnClickListener {
            onBackPressed()
        }

    }
}