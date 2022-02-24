package com.example.githubsearch.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.githubsearch.api.Client
import com.example.githubsearch.api.Service
import com.example.githubsearch.dataclass.Item
import com.example.githubsearch.pagination.UserRemoteMediator
import com.example.githubsearch.room.AppDatabase
import com.example.githubsearch.utilities.PAGE_SIZE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class Repository(private val db: AppDatabase) {
    private val service: Service = Client.client!!.create(Service::class.java)

    @OptIn(ExperimentalPagingApi::class)
    fun fetchPosts(name: String): Flow<PagingData<Item>> {
        return Pager(
            PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = UserRemoteMediator(name, db, service),
            pagingSourceFactory = { db.userDao().getAll() }
        ).flow.flowOn(Dispatchers.Default)
    }

    fun getAllData(name: String): Flow<PagingData<Item>> {
        return Pager(PagingConfig(PAGE_SIZE)) {
            db.userDao().getSearchData(name)
        }.flow.flowOn(Dispatchers.Default)
    }
}