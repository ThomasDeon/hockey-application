package com.example.hockeyapp.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CoachDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoach(coach: Coach)

    @Update
    suspend fun updateCoach(coach: Coach)

    @Delete
    suspend fun deleteCoach(coach: Coach)

    @Query("SELECT * FROM coaches")
    fun getAllCoaches(): Flow<List<Coach>>

    @Query("DELETE FROM coaches WHERE id = :coachId")
    suspend fun deleteCoachById(coachId: Int)
}
