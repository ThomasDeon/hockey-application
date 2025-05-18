// ViewModelFactory.kt
package com.example.hockeyapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hockeyapp.Repository

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(UserViewModel::class.java) -> UserViewModel(repository) as T
            modelClass.isAssignableFrom(CoachViewModel::class.java) -> CoachViewModel(repository) as T
            modelClass.isAssignableFrom(TeamViewModel::class.java) -> TeamViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
