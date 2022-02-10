package com.tarun.myapplication.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.tarun.myapplication.dataclass.Item
import com.tarun.myapplication.pagination.UserDatBaseSource
import com.tarun.myapplication.reposiotry.Repository
import com.tarun.myapplication.room.AppDatabase
import com.tarun.myapplication.room.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val myRepository by lazy { Repository(database) }
    private val database = AppDatabase.getInstance(application)


    fun pagedData(query: String): LiveData<PagingData<Item>> {

        return myRepository.getData(query)
    }

    fun getDataBAseData(): Flow<PagingData<User>> {
        return myRepository.getDbData().map {
            it.map { user ->
                Log.e("TAG", "getDataBAseData: ${user.login}", )
                User(
                    user.id,
                    user.login,
                    user.html_url,
                    user.avatar_url
                )
            }
        }.flowOn(Dispatchers.IO).cachedIn(viewModelScope)
    }

}



