package com.example.demoapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserResponse (@PrimaryKey var id:Int, var title:String, var body:String, var userId : Int)