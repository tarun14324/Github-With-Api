package com.tarun.myapplication.dataclass

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="user")
data class Item(
    @ColumnInfo val avatar_url: String?=null,
    @ColumnInfo val events_url: String?=null,
    @ColumnInfo val followers_url: String?=null,
    @ColumnInfo val following_url: String?=null,
    @ColumnInfo val gists_url: String?=null,
    @ColumnInfo val gravatar_id: String?=null,
    @ColumnInfo val html_url: String?=null,
    @PrimaryKey(autoGenerate = true) var id: Int?=0,
    @ColumnInfo val login: String?=null,
    @ColumnInfo val node_id: String?=null,
    @ColumnInfo val organizations_url: String?=null,
    @ColumnInfo val received_events_url: String?=null,
    @ColumnInfo val repos_url: String?=null,
    @ColumnInfo val score: Double?=null,
    @ColumnInfo val site_admin: Boolean?=null,
    @ColumnInfo val starred_url: String?=null,
    @ColumnInfo val subscriptions_url: String?=null,
    @ColumnInfo val type: String?=null,
    @ColumnInfo val url: String?=null
)