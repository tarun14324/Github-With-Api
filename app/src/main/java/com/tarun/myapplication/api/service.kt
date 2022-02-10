package com.tarun.myapplication.api

import com.tarun.myapplication.dataclass.Data
import retrofit2.http.GET
import retrofit2.http.Query


interface Service {

    @GET("search/users")
    suspend fun getRepo(
        @Query("q") name: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
        ): Data
}