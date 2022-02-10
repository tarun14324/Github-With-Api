package com.tarun.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.tarun.myapplication.dataclass.Item
import com.tarun.myapplication.reposiotry.Repository
import com.tarun.myapplication.room.AppDatabase
import com.tarun.myapplication.room.User
import kotlinx.coroutines.flow.Flow


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val myRepository by lazy { Repository(database) }
    private val database = AppDatabase.getInstance(application)


    fun pagedData(query: String): LiveData<PagingData<Item>> {
        return myRepository.getData(query)
    }

//remote
    fun RemoteData(query: String): Flow<PagingData<Item>> {
        return myRepository.fetchPosts(query)
    }


}



