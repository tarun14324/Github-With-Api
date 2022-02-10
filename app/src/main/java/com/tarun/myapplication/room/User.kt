package com.tarun.myapplication.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) var id: Int?=0,
    @ColumnInfo var login: String?=null,
    @ColumnInfo var html_url: String?=null,
    @ColumnInfo var avatar_url: String?=null
    )



