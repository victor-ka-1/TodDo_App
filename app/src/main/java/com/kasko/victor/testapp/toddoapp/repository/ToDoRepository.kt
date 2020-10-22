package com.kasko.victor.testapp.toddoapp.repository

import androidx.lifecycle.LiveData
import com.kasko.victor.testapp.toddoapp.api.ToDoApiService
import com.kasko.victor.testapp.toddoapp.api.models.ToDoTaskRemote
import com.kasko.victor.testapp.toddoapp.api.models.UserRemote
import com.kasko.victor.testapp.toddoapp.db.dao.ToDoDao
import com.kasko.victor.testapp.toddoapp.db.dao.UserDao
import com.kasko.victor.testapp.toddoapp.db.entity.*


interface IToDoRepository {
    suspend fun refreshDataBase()
    fun getAllUsers():LiveData<List<UserLocal>>
    fun getTasksWithUser() : LiveData<List<TaskWithUser>>
    suspend fun getTasksForUser(id:Int) : List<TaskWithUser>
}

class ToDoRepository(private val apiService: ToDoApiService,
                     private val userDao: UserDao,
                     private val toDoDao: ToDoDao) : IToDoRepository {


    override suspend fun refreshDataBase() {
        val users = getUsersFromServer()
        userDao.deleteAllUsers()
        userDao.insertUserList(users)

        val tasks = getTasksFromServer()
        toDoDao.deleteAllTasks()
        toDoDao.insertTaskList(tasks)
    }


    override fun getAllUsers(): LiveData<List<UserLocal>> {
        return userDao.getAllUsers()
    }

    override fun getTasksWithUser(): LiveData<List<TaskWithUser>> {
        return toDoDao.getAllTasksWithUser()
    }

    override suspend  fun getTasksForUser(id: Int): List<TaskWithUser> {
        return toDoDao.getTasksForUser(user_id = id)
    }


    private suspend fun getTasksFromServer(): List<ToDoTaskLocal> =
        apiService.getToDoTasks().map { taskRemote -> taskRemote.toToDoTaskLocal() }

    private suspend fun getUsersFromServer(): List<UserLocal> =
        apiService.getUsers().map { userRemote -> userRemote.toUserLocal() }


    private fun ToDoTaskRemote.toToDoTaskLocal() : ToDoTaskLocal =
        ToDoTaskLocal(
            userId = this.userId,
            taskId = this.taskId,
            title = this.title,
            isCompleted = this.isCompleted)

    private fun UserRemote.toUserLocal() : UserLocal = UserLocal(
        id = this.id,
        name = this.name,
        username = this.usermane,
        email = this.email,
        address = AddressLocal(
            street = this.address.street,
            suite = this.address.suite,
            city = this.address.city,
            zipCode = this.address.zipCode,
            geoPosition = GeoPositionLocal(
                latitude = this.address.geoPosition.latitude,
                longitude = this.address.geoPosition.longitude
            )
        ),
        phone = this.phone,
        website = this.website,
        company = CompanyLocal(
            companyName = this.company.companyName,
            catchPhrase = this.company.catchPhrase,
            bs = this.company.bs
        )
    )
}
