package com.kasko.victor.testapp.toddoapp.api.models

import com.google.gson.annotations.SerializedName

data class ToDoTaskRemote(
    @SerializedName("userId") val userId: Int,
    @SerializedName("id") val taskId :Int,
    @SerializedName("title") val title : String,
    @SerializedName("completed") val isCompleted: Boolean = false
)
