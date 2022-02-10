package com.tarun.myapplication.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo var login: String,
    @ColumnInfo var html_url: String,
    @ColumnInfo var avatar_url: String,
    )



