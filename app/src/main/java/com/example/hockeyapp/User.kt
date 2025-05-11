package com.example.hockeyapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table" )
data class User(
    @PrimaryKey(autoGenerate = true) val pId : Int = 0,
    @ColumnInfo("user_fullname") val fullname:String,
    @ColumnInfo("user_age") val age:Int,
    @ColumnInfo("user_username") val username : String,
    @ColumnInfo("user_email")val email: String,
    @ColumnInfo("user_password")val password: String,
    @ColumnInfo("user_role")val role:String
)
