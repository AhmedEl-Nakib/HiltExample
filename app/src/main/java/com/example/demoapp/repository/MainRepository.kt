package com.example.demoapp.repository

import com.example.demoapp.api.ApiService
import com.example.demoapp.models.Employee
import com.example.demoapp.models.EmployeeResponse
import com.example.demoapp.models.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService){

//    suspend fun getEmployee() = apiHelper.getEmployees()


    suspend fun getFlowEmployee(): Flow<List<UserResponse>?> {
        return flow {
            emit(apiService.fetchPosts().body()!!)
        }.flowOn(Dispatchers.IO)
    }
}