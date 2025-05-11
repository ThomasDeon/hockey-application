package com.example.hockeyapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.hockeyapp.Repository
import com.example.hockeyapp.User
import kotlinx.coroutines.launch

class UserViewModel(private val repository: Repository ): ViewModel(){
    fun getUser() =repository.getAllData().asLiveData(viewModelScope.coroutineContext)

    fun updateUser(user: User){
        viewModelScope.launch {
            repository.updateUser(user)

        }
    }
    fun deleteUser(user: User){
        viewModelScope.launch {
            repository.deleteUser(user)
        }
    }  fun savePerson(user: User){
        viewModelScope.launch {
            repository.savePerson(user)

        }
    }
}