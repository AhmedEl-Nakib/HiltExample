package com.example.demoapp.repository

import android.content.Context
import com.example.demoapp.api.ApiService
import com.example.demoapp.db.AppDatabase
import com.example.demoapp.models.UserResponse
import com.example.demoapp.other.NetworkFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService, private val ctx: Context , private val db:AppDatabase) : NetworkFactory(ctx){

    suspend fun getFlowEmployee(): Flow<List<UserResponse>?> {
        return flow {
            if(isOnline()) {
                val result = apiService.fetchPosts()
                db.userDao().deleteAllDataFromResponse()
                db.userDao().addResponseToDB(result.body()!!)
                emit(result.body()!!)
                showApiResult(result.body()!!.toString() , result.raw().toString())
            }else {
                emit(db.userDao().getAllDataFromResponse())
            }
        }.flowOn(Dispatchers.IO)
    }

}