package com.example.demoapp.repository

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.demoapp.api.ApiService
import com.example.demoapp.db.AppDatabase
import com.example.demoapp.models.Employee
import com.example.demoapp.models.EmployeeResponse
import com.example.demoapp.models.UserResponse
import com.example.demoapp.other.Constants
import com.example.demoapp.other.NetworkFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService, private val ctx: Context) : NetworkFactory(ctx){

//    suspend fun getEmployee() = apiHelper.getEmployees()


//    suspend fun getFlowEmployee(): Flow<List<UserResponse>?> {
//        return flow {
//            emit(apiService.fetchPosts().body()!!)
//        }.flowOn(Dispatchers.IO)
//    }
//



    suspend fun getFlowEmployee(): Flow<List<UserResponse>?> {
        val db = Room.databaseBuilder(ctx, AppDatabase::class.java, Constants.DBName).build()
        return flow {
            if(isNetworkConnected()) {
                val result = apiService.fetchPosts().body()!!
                db.userDao().deleteAllDataFromResponse()
                db.userDao().addResponseToDB(result)
                emit(result)
            }else {
                emit(db.userDao().getAllDataFromResponse())
            }
        }.flowOn(Dispatchers.IO)
    }


}