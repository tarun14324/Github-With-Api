package com.example.githubsearch.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubsearch.dataclass.Item


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<Item>)

    @Query("SELECT * FROM user ORDER BY login ASC")
    fun getAll(): PagingSource<Int, Item>


    @Query("SELECT * FROM user where login = :name ORDER BY login ASC")
    fun getSearchData(name: String): PagingSource<Int, Item>

    @Query("DELETE FROM User")
    suspend fun clearAll()

    @Query("SELECT COUNT(id) from user")
    suspend fun count(): Int


}


