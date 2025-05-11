package com.example.hockeyapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(version = 1 ,entities = [User::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao

    companion object{


       @Volatile
       private var INSTANCE : AppDatabase? = null
        fun getDatabase(context : Context) : AppDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java,
                    "UserDatabase").build()
                INSTANCE = instance
                return instance

            }
        }
    }
}