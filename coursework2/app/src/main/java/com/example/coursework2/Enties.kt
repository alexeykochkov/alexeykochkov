package com.example.coursework2

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoTableDao {

    @Query("SELECT * FROM photo_table")  //что-то не подтягивает селект и фром
    fun showAllPhoto(): Flow<List<PhotoTable>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(table: PhotoTable)

    @Query("SELECT * FROM photo_table WHERE filmId == :filmId")
    suspend fun getFilm (filmId: Int): PhotoTable?
}

//модель таблицы
@Entity(tableName = "photo_table")
data class PhotoTable(
    @PrimaryKey
    @ColumnInfo(name = "filmId")
    val filmId: Int,
    @ColumnInfo(name = "beLoved")
    var beLoved: Boolean,
    @ColumnInfo(name = "wantToSee")
    var wantToSee: Boolean,
    @ColumnInfo(name = "collectionName")
    val collectionName: String
)