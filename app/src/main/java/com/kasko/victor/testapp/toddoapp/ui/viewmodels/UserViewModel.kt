package com.kasko.victor.testapp.toddoapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kasko.victor.testapp.toddoapp.repository.IToDoRepository
import com.kasko.victor.testapp.toddoapp.db.entity.TaskWithUser
import kotlinx.coroutines.launch

class UserViewModel(private val repository: IToDoRepository) : ViewModel(){

    private val _userTasksLiveData: MutableLiveData<List<TaskWithUser>> = MutableLiveData()
    val userTasksLiveData: LiveData<List<TaskWithUser>> = _userTasksLiveData

    fun getTasksForUser(userId: Int){
        viewModelScope.launch { _userTasksLiveData.postValue( repository.getTasksForUser(userId) )}
    }


}