package com.example.demoapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.demoapp.models.UserResponse

@Database(entities = arrayOf(UserResponse::class), version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao

}