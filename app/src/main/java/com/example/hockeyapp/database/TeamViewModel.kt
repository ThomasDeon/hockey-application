package com.example.hockeyapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.hockeyapp.Repository
import com.example.hockeyapp.database.Team
import kotlinx.coroutines.launch

class TeamViewModel(private val repository: Repository) : ViewModel() {

    fun getAllTeams() = repository.getAllTeams().asLiveData(viewModelScope.coroutineContext)

    fun saveTeam(team: Team) {
        viewModelScope.launch {
            repository.saveTeam(team)
        }
    }

    fun updateTeam(team: Team) {
        viewModelScope.launch {
            repository.updateTeam(team)
        }
    }

    fun deleteTeam(team: Team) {
        viewModelScope.launch {
            repository.deleteTeam(team)
        }
    }
}
