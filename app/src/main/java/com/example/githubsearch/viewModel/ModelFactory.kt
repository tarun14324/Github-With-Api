package com.example.githubsearch.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            MyViewModel(application) as T
        } else {
            throw IllegalArgumentException("No ViewModel")
        }
    }

}



