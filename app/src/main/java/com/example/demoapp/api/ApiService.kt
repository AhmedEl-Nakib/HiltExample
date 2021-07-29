package com.example.demoapp.api

import com.example.demoapp.models.EmployeeResponse
import com.example.demoapp.models.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService{

    @GET("employees")
    suspend fun getEmployees():Response<EmployeeResponse>

    @GET("posts")
    suspend fun fetchPosts() : Response<List<UserResponse>>


//    @GET("employees")
//    suspend fun getEmployees2():Response<EmployeeResponse>

}