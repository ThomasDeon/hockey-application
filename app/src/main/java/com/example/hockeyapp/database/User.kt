package com.example.hockeyapp.database
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table" )
data class User(
    @PrimaryKey(autoGenerate = true) val pId : Int = 0,
    @ColumnInfo("user_firstname") val firstname:String,
    @ColumnInfo("user_lastname") val lastname : String,
    @ColumnInfo("user_email")val email: String,
    @ColumnInfo("user_password")val password: String,

)