package com.example.coursework2

import Database
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
//import Database
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.room.Room



class MainActivity : AppCompatActivity() {

    private val viewModel: MVM by viewModels()

    //инициализация базы данных
//    var database = Room.inMemoryDatabaseBuilder(this, Database::class.java).build()


    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}

//> Task :app:kaptDebugKotlin
//C:\Users\Alex Alex\Desktop\MyRepository\GitLabRepository_Android_lvl_1\coursework2\app\build\tmp\kapt3\stubs\debug\Database.java:6: warning: Schema export directory is not provided to the annotation processor so we cannot export the schema. You can either provide `room.schemaLocation` annotation processor argument OR set exportSchema to false.
//public abstract class Database extends androidx.room.RoomDatabase {
//    ^