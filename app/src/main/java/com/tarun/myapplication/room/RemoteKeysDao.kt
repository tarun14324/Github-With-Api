package com.tarun.myapplication.room

import androidx.room.*

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeys>)

    @Query("SELECT * FROM remote_keys WHERE repoId = :repoId")
    suspend fun remoteKeysRepoId(repoId: Int?): RemoteKeys?

    @Query("DELETE FROM remote_keys")
    suspend fun clearRemoteKeys()
}


@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey val repoId: Int?,
    val prevKey: Int?,
    val nextKey: Int?
)