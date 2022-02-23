package com.example.githubsearch.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.githubsearch.dataclass.Item
import com.example.githubsearch.repository.Repository
import com.example.githubsearch.room.AppDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch


class MyViewModel constructor(app: Application) :
    AndroidViewModel(app) {
    private val db by lazy { AppDatabase.getInstance(app.applicationContext) }

    private val repo: Repository by lazy {
        Repository(db)
    }
    var isLoading = MutableLiveData<Boolean>()
    val userName by lazy { MutableStateFlow("") }

    @OptIn(ExperimentalCoroutinesApi::class)
    @FlowPreview
    val items by lazy {
        userName.flatMapLatest {
            repo.fetchPosts(it).cachedIn(viewModelScope)
        }

    }

    val itemsUpdated by lazy { MutableLiveData<PagingData<Item>>() }


    init {
        isLoading.value=true
        viewModelScope.launch {
            items.collectLatest {
                itemsUpdated.postValue(it)
            }
        }

    }

    fun getAllRoomData(name: String): Flow<PagingData<Item>> {
        return repo.getAllData(name)
    }


}

