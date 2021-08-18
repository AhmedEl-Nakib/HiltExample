package com.example.demoapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.demoapp.models.UserResponse

@Dao
interface UserDao {

    @Insert
    fun addResponseToDB(response : List<UserResponse>)

    @Query("DELETE FROM UserResponse") // Delete All Users
    fun deleteAllDataFromResponse()

    @Query("SELECT * FROM UserResponse")
    fun getAllDataFromResponse() : List<UserResponse>


}