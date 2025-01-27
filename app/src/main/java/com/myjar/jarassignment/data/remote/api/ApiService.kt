package com.myjar.jarassignment.data.remote.api

import com.myjar.jarassignment.data.remote.model.ComputerItem
import retrofit2.http.GET

interface ApiService {
    @GET("/objects")
    suspend fun fetchResults(): List<ComputerItem>
}