package com.example.coursework2.database
import androidx.room.*
import androidx.room.Database



@Database(entities = [FilmEntity::class, CollectionEntity:: class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun tableDao(): FilmTableDao

    abstract fun tableDaoCollection(): CollectionTableDao
}



