package com.tarun.myapplication.reposiotry

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.tarun.myapplication.api.Client
import com.tarun.myapplication.api.Service
import com.tarun.myapplication.dataclass.Item
import com.tarun.myapplication.pagination.UserDatBaseSource
import com.tarun.myapplication.pagination.UserSource
import com.tarun.myapplication.room.AppDatabase
import com.tarun.myapplication.room.User
import kotlinx.coroutines.flow.Flow


class Repository(private val database: AppDatabase) {
    private val service: Service = Client.client!!.create(Service::class.java)
    fun getData(query: String): LiveData<PagingData<Item>> = Pager(
        config = PagingConfig(10, 4, false),
        pagingSourceFactory = { UserSource(service, query, database) }
    ).liveData


    fun getDbData(): Flow<PagingData<User>> {
        return Pager(PagingConfig(PAGE_SIZE, initialLoadSize = 5, enablePlaceholders = false)) {
            UserDatBaseSource(database)
        }.flow
    }

    companion object {
        private
        const val PAGE_SIZE = 1
    }

}

