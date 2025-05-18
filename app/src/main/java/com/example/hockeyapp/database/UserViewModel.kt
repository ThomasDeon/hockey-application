package com.example.hockeyapp.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.hockeyapp.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

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

