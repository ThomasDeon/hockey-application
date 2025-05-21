package com.example.hockeyapp.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.hockeyapp.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

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
