package com.tarun.myapplication.reposiotry

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.tarun.myapplication.api.Client
import com.tarun.myapplication.api.Service
import com.tarun.myapplication.dataclass.Item
import com.tarun.myapplication.pagination.UserRemoteMediatore
import com.tarun.myapplication.pagination.UserSource
import com.tarun.myapplication.room.AppDatabase
import kotlinx.coroutines.flow.Flow


class Repository(private val database: AppDatabase) {
    private val service: Service = Client.client!!.create(Service::class.java)

    // usimg paging source
    fun getData(query: String): LiveData<PagingData<Item>> = Pager(
        config = PagingConfig(20, 4, false),
        pagingSourceFactory = { UserSource(service, query, database) }
    ).liveData

    //remote mediaotr
    @OptIn(ExperimentalPagingApi::class)
    fun fetchPosts(query: String): Flow<PagingData<Item>> {
        return Pager(
            PagingConfig(
                pageSize = 40,
                enablePlaceholders = false,
                // 1
                prefetchDistance = 3
            ),

            // 2
            remoteMediator = UserRemoteMediatore(query, service, database),

            // 3
            pagingSourceFactory = { database.userDao().getAll() }
        ).flow
    }


}

