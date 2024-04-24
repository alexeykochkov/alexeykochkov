package com.example.coursework2.database

import android.app.Application
import androidx.room.Room





class AppDatabase : Application() {

    lateinit var database: Database


    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(applicationContext, Database::class.java, name = "photo_table").build()

//        viewModel.databaseMVM = Room.inMemoryDatabaseBuilder(this, Database::class.java).build()
    }
}