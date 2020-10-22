package com.kasko.victor.testapp.toddoapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kasko.victor.testapp.toddoapp.db.dao.ToDoDao
import com.kasko.victor.testapp.toddoapp.db.dao.UserDao
import com.kasko.victor.testapp.toddoapp.db.entity.ToDoTaskLocal
import com.kasko.victor.testapp.toddoapp.db.entity.UserLocal

@Database(entities = [ UserLocal::class, ToDoTaskLocal::class], version = 1)
abstract class MyDataBase :RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
    abstract fun userDao(): UserDao

}