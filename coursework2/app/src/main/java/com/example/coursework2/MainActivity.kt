package com.example.coursework2


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import androidx.activity.viewModels

import androidx.room.Room
import com.example.coursework2.database.AppDatabase


class MainActivity : AppCompatActivity() {

    private val viewModel: MVM by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {

//        viewModel.databaseMVM = (application as AppDatabase).database
//        viewModel.addRequiredCollectionInCollectionTable()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
