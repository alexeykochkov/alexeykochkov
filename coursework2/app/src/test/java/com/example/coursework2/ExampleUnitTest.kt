package com.example.coursework2

import Database

import android.content.Context
import androidx.room.Room

import kotlinx.coroutines.runBlocking
import org.junit.Test
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

//class Photos(database: Database) {
//    var db = database
//    var dao = db.tableDao()
//    suspend fun insert(filmId: Int) {
//        dao.insertPhoto(
//            PhotoTable(
//                filmId = filmId,
//                beLoved = false,
//                wantToSee = false,
//                collectionName = ""
//            )
//        )
//    }
//
//    suspend fun getAll(): List<PhotoTable> {
//        val sof = getAllAsFlow()
//        var print = sof.first()
//        return print
//    }
//
//    fun getAllAsFlow(): Flow<List<PhotoTable>> {
//        return dao.showAllPhoto()
//    }
//}


@RunWith(RobolectricTestRunner::class)
class ExampleUnitTest {
//    @Test
//    fun addition_isCorrect() {
//        RetrofitInstance()
//        var retrofit = RetrofitInstance().premieresApi
//        runBlocking {
//            val value = retrofit.getInformationPremieres(2020, "JANUARY")
//            println("${value.total}")
//        }
//    }
//
//
//    @Test
//    fun daoTest() {
//        var db = getTestDb()
//        var photos = Photos(db)
//        runBlocking {
//            photos.insert(1)
//        }
//
//    }
//
//    fun getTestDb(): Database {
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        var db = Room.inMemoryDatabaseBuilder(context, Database::class.java).build()
//        return db
//    }
}



