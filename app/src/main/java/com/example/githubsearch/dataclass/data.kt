package com.example.githubsearch.dataclass

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Data(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)

@Entity(tableName = "user")
data class Item(
    @ColumnInfo val avatar_url: String? = null,
    @ColumnInfo val html_url: String? = null,
    @PrimaryKey(autoGenerate = true) var id: Int? = 0,
    @ColumnInfo val login: String? = null,
    @ColumnInfo val repos_url: String? = null,

    )


