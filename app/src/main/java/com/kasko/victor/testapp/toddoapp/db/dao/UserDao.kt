package com.kasko.victor.testapp.toddoapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kasko.victor.testapp.toddoapp.db.entity.ToDoTaskLocal
import com.kasko.victor.testapp.toddoapp.db.entity.UserLocal

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserList(list : List<UserLocal> )

    @Delete
    suspend fun delete(user: UserLocal)

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM users")
    fun getAllUsers(): LiveData<List<UserLocal>>
}
