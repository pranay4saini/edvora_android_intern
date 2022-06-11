package com.example.edvoraandroidintern.data.network

import com.example.edvoraandroidintern.data.model.Ride
import com.example.edvoraandroidintern.data.model.User
import retrofit2.http.GET

interface ApiService {
   @GET("/user")
   suspend fun getUser(): User

   @GET("/rides")
   suspend fun getRides(): List<Ride>
}