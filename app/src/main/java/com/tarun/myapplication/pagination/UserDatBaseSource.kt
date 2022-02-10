package com.tarun.myapplication.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tarun.myapplication.room.AppDatabase
import com.tarun.myapplication.room.User
import retrofit2.HttpException
import java.io.IOException

class UserDatBaseSource(private val database: AppDatabase) :
    PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val position = params.key ?: START_INDEX
        return try {
            val data = database.userDao().getAll()
            Log.e("TAG", "load: ${data.isEmpty()}", )
            LoadResult.Page(
                data = data,
                prevKey = if (position == START_INDEX) null else position - 1,
                nextKey = position.plus(1)
            )
        } catch (e: Exception) {
            val error = IOException("Unable To Get Data")
            LoadResult.Error(error)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }


}