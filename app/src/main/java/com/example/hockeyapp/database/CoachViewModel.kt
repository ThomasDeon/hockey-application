package com.example.hockeyapp.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.hockeyapp.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoachViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

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
