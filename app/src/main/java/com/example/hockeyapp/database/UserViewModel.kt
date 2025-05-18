package com.example.hockeyapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.hockeyapp.Repository
import com.example.hockeyapp.database.User
import kotlinx.coroutines.launch

class UserViewModel(private val repository: Repository) : ViewModel() {

    fun getUser() = repository.getAllUsers().asLiveData(viewModelScope.coroutineContext)

    fun savePerson(user: User) {
        viewModelScope.launch {
            repository.saveUser(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            repository.deleteUser(user)
        }
    }

    fun deleteUserById(pId: Int) {
        viewModelScope.launch {
            repository.deleteUserById(pId)
        }
    }
}
