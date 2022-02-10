package com.tarun.myapplication.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tarun.myapplication.dataclass.Item

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: User)

    @Query("SELECT * FROM user ORDER BY login DESC")
    fun getAll(): List<User>

    @Query("DELETE FROM User")
    suspend fun clearAll()

    @Query("SELECT COUNT(id) from user")
    suspend fun count(): Int
}


