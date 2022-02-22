package com.example.githubsearch.api

import com.example.githubsearch.dataclass.Data
import retrofit2.http.GET
import retrofit2.http.Query


interface Service {

    @GET("users")
    suspend fun getRepo(
        @Query("q") name: String,
        @Query("page") page: Int
    ): Data



    @GET("search/users")
    suspend fun getDataRepo(
        @Query("q") name: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
    ): Data

}