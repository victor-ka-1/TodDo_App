package com.kasko.victor.testapp.toddoapp.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class TaskWithUser(
    @Embedded
    val task: ToDoTaskLocal,
    @Relation(parentColumn = "userId", entityColumn = "id")
    val userLocal: UserLocal?
)