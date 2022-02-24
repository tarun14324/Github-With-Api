package com.example.githubsearch.api

import com.example.githubsearch.utilities.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object Client {

    var retrofit: Retrofit? = null
    val client: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
}
