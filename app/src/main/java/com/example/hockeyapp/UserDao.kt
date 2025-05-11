package com.example.hockeyapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow



@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePerson(user: User)

   @Update
   suspend fun updateUser(user: User)

   @Delete
   suspend fun deleteUser(user: User)

   @Query("DELETE FROM user_table WHERE pId = :pId")
   suspend fun deleteUserById(pId : Int)

   @Query("SELECT * FROM user_table ")
   fun getAllData() : Flow<List<User>>
}