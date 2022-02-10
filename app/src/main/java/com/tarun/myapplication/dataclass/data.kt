package com.tarun.myapplication.dataclass

import androidx.room.Entity

@Entity
data class Data(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)


