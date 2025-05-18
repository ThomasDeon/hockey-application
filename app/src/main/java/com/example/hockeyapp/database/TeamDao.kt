package com.example.hockeyapp.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeam(team: Team)

    @Update
    suspend fun updateTeam(team: Team)

    @Delete
    suspend fun deleteTeam(team: Team)

    @Query("SELECT * FROM teams")
    fun getAllTeams(): Flow<List<Team>>

    @Query("DELETE FROM teams WHERE id = :teamId")
    suspend fun deleteTeamById(teamId: Int)
}
