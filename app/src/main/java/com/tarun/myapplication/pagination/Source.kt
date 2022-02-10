package com.tarun.myapplication.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tarun.myapplication.api.Service
import com.tarun.myapplication.dataclass.Item
import com.tarun.myapplication.room.AppDatabase
import retrofit2.HttpException
import java.io.IOException

const val START_INDEX = 0

class UserSource(private val apiService: Service, text: String, private val database: AppDatabase) :
    PagingSource<Int, Item>() {
    private val name = text
    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val position = params.key ?: START_INDEX
        return try {
            val data = apiService.getRepo(name, position,params.loadSize)
            database.userDao().insertAll(data.items)
            LoadResult.Page(
                data = data.items,
                prevKey = if (position == START_INDEX) null else position - 1,
                nextKey = if (data.items.isEmpty()) null else position.plus(1)
            )
        } catch (e: Exception) {
            val error = IOException("Please Check Internet Connection")
            LoadResult.Error(error)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }


}