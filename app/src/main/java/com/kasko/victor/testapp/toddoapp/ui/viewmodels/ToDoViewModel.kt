package com.kasko.victor.testapp.toddoapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kasko.victor.testapp.toddoapp.repository.IToDoRepository
import com.kasko.victor.testapp.toddoapp.db.entity.TaskWithUser
import com.kasko.victor.testapp.toddoapp.db.entity.UserLocal
import com.kasko.victor.testapp.toddoapp.utils.emit
import com.kasko.victor.testapp.toddoapp.utils.emptySingleLiveEvent
import kotlinx.coroutines.launch

class ToDoViewModel(private val repository: IToDoRepository) : ViewModel(){
    init {
        viewModelScope.launch {
            try{
                repository.refreshDataBase()
            } catch (e: Exception){
                _errorLiveData.emit(" ERROR: ${e.message} ")
            }
        }
    }
    private val _errorLiveData  = emptySingleLiveEvent<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    val allUsersLiveData :LiveData< List<UserLocal>> = repository.getAllUsers()

    val tasksWithUser:LiveData< List<TaskWithUser>> = repository.getTasksWithUser()
}