package com.kasko.victor.testapp.toddoapp.api

import com.kasko.victor.testapp.toddoapp.api.models.ToDoTaskRemote
import com.kasko.victor.testapp.toddoapp.api.models.UserRemote
import retrofit2.Response
import retrofit2.http.GET

interface ToDoApiService {
    @GET("todos")
    suspend fun getToDoTasks():List<ToDoTaskRemote>
    @GET("users")
    suspend fun getUsers(): List<UserRemote>
}