package com.example.hockeyapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hockeyapp.database.Coach
import com.example.hockeyapp.database.CoachDao
import com.example.hockeyapp.database.Team
import com.example.hockeyapp.database.TeamDao
import com.example.hockeyapp.database.User
import com.example.hockeyapp.database.UserDao

@Database(
    version = 1,
    entities = [User::class, Coach::class, Team::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun coachDao(): CoachDao
    abstract fun teamDao(): TeamDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "UserDatabase"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
