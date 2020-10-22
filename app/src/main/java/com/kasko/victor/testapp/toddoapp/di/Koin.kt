package com.kasko.victor.testapp.toddoapp.di

import androidx.room.Room
import com.kasko.victor.testapp.toddoapp.repository.IToDoRepository
import com.kasko.victor.testapp.toddoapp.repository.ToDoRepository
import com.kasko.victor.testapp.toddoapp.ui.viewmodels.ToDoViewModel
import com.kasko.victor.testapp.toddoapp.api.ToDoApiClient
import com.kasko.victor.testapp.toddoapp.db.MyDataBase
import com.kasko.victor.testapp.toddoapp.ui.viewmodels.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val roomDBmodule = module {
    single {
        Room.databaseBuilder( get(), MyDataBase::class.java, "task_database")
            .build()
    }
    single { get<MyDataBase>().toDoDao() }
    single { get<MyDataBase>().userDao() }
}

val viewModelModule = module {
    viewModel { ToDoViewModel(repository = get()) }
    viewModel { UserViewModel(repository = get()) }
}

val appModule = module {
    single { ToDoRepository(apiService = get(), userDao = get(), toDoDao = get()) as IToDoRepository }
}

val apiModule = module {
    single { ToDoApiClient.okHttpClient() }
    single { ToDoApiClient.getApiClient(okHttpClient = get() ) }
    single { ToDoApiClient.getApiService(retrofit = get() ) }
}