package com.example.hockeyapp
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

class Repository(private val db : AppDatabase) {

    suspend fun savePerson(user: User){
        db.userDao().savePerson(user)
    }


    suspend fun updateUser(user: User){
        db.userDao().updateUser(user)
    }


    suspend fun deleteUser(user: User){
        db.userDao().deleteUser(user)

    }


    suspend fun deleteUserById(pId : Int){
        db.userDao().deleteUserById(pId)
    }


    fun getAllData() : Flow<List<User>>{
        return db.userDao().getAllData()
    }



}