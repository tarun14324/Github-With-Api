package com.example.githubsearch.repository

import androidx.paging.*
import com.example.githubsearch.api.Client
import com.example.githubsearch.api.Service
import com.example.githubsearch.dataclass.Item
import com.example.githubsearch.pagination.UserRemoteMediator
import com.example.githubsearch.room.AppDatabase
import com.example.githubsearch.utilities.PAGE_SIZE
import kotlinx.coroutines.flow.Flow

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
        ).flow
    }

    fun getAllData(name: String): Flow<PagingData<Item>> {
        return Pager(PagingConfig(PAGE_SIZE)) {
            db.userDao().getSearchData(name)
        }.flow
    }


}