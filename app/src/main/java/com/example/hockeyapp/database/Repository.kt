package com.example.hockeyapp

import com.example.hockeyapp.database.Coach
import com.example.hockeyapp.database.Team
import com.example.hockeyapp.database.User
import kotlinx.coroutines.flow.Flow

class Repository(private val db: AppDatabase) {

    //  USER
    suspend fun saveUser(user: User) {
        db.userDao().savePerson(user)
    }

    suspend fun updateUser(user: User) {
        db.userDao().updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        db.userDao().deleteUser(user)
    }

    suspend fun deleteUserById(pId: Int) {
        db.userDao().deleteUserById(pId)
    }

    fun getAllUsers(): Flow<List<User>> {
        return db.userDao().getAllData()
    }

    // COACH
    suspend fun saveCoach(coach: Coach) {
        db.coachDao().insertCoach(coach)
    }

    suspend fun updateCoach(coach: Coach) {
        db.coachDao().updateCoach(coach)
    }

    suspend fun deleteCoach(coach: Coach) {
        db.coachDao().deleteCoach(coach)
    }

    fun getAllCoaches(): Flow<List<Coach>> {
        return db.coachDao().getAllCoaches()
    }

    //  TEAM
    suspend fun saveTeam(team: Team) {
        db.teamDao().insertTeam(team)
    }

    suspend fun updateTeam(team: Team) {
        db.teamDao().updateTeam(team)
    }

    suspend fun deleteTeam(team: Team) {
        db.teamDao().deleteTeam(team)
    }

    fun getAllTeams(): Flow<List<Team>> {
        return db.teamDao().getAllTeams()
    }
}
