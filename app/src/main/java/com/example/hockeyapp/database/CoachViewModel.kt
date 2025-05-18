package com.example.hockeyapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.hockeyapp.Repository
import com.example.hockeyapp.database.Coach
import kotlinx.coroutines.launch

class CoachViewModel(private val repository: Repository) : ViewModel() {

    fun getAllCoaches() = repository.getAllCoaches().asLiveData(viewModelScope.coroutineContext)

    fun saveCoach(coach: Coach) {
        viewModelScope.launch {
            repository.saveCoach(coach)
        }
    }

    fun updateCoach(coach: Coach) {
        viewModelScope.launch {
            repository.updateCoach(coach)
        }
    }

    fun deleteCoach(coach: Coach) {
        viewModelScope.launch {
            repository.deleteCoach(coach)
        }
    }
}
