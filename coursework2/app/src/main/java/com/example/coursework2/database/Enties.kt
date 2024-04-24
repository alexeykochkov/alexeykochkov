package com.example.coursework2.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.jsonArray

const val TABLE_NAME = "film_table"
const val TABLE_NAME_COLLECTION = "collection_table"

@Dao
interface FilmTableDao {

    @Query("SELECT * FROM $TABLE_NAME")  //что-то не подтягивает селект и фром
    fun showAllFilms(): Flow<List<FilmEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(table: FilmEntity)

    @Query("SELECT * FROM $TABLE_NAME WHERE filmId == :filmId")
    suspend fun getFilm(filmId: Int): FilmEntity?
}



@Dao
interface CollectionTableDao {

    @Query("SELECT * FROM $TABLE_NAME_COLLECTION")  //что-то не подтягивает селект и фром
    fun showAllCollection(): Flow<List<CollectionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilmInCollection(table: CollectionEntity)

    @Query("SELECT * FROM $TABLE_NAME_COLLECTION WHERE filmId == :filmId")
    suspend fun getFilminCollection(filmId: Int): CollectionEntity?
}


//модель таблицы
@Entity(tableName = TABLE_NAME)
data class FilmEntity(
    @PrimaryKey
    @ColumnInfo(name = "filmId")
    val filmId: Int,
    @ColumnInfo(name = "beLoved")
    var beLoved: Boolean = false,
    @ColumnInfo(name = "wantToSee")
    var wantToSee: Boolean = false,
    @ColumnInfo(name = "viewed")
    var viewed: Boolean = false,
    @ColumnInfo(name = "collectionName")
    val collectionName: String = ""
)


class SafeListSerializerStack<E>(private val elementSerializer: KSerializer<E>) : KSerializer<List<E>> {
    private val listSerializer = ListSerializer(elementSerializer)
    override val descriptor: SerialDescriptor = listSerializer.descriptor
    override fun serialize(encoder: Encoder, value: List<E>) {
        listSerializer.serialize(encoder, value)
    }
    override fun deserialize(decoder: Decoder): List<E> = with(decoder as JsonDecoder) {
        decodeJsonElement().jsonArray.mapNotNull {
            try {
                json.decodeFromJsonElement(elementSerializer, it)
            } catch (e: SerializationException) {
                e.printStackTrace()
                null
            }
        }
    }
}


//@Serializer(forClass = ArrayList::class)
//object MyListItemsListSerializer: KSerializer<ArrayList<Int>>

@Entity(tableName = TABLE_NAME_COLLECTION)
data class CollectionEntity(
    @PrimaryKey
    @ColumnInfo(name = "collectionName")
    val collectionName: String = "",

    @ColumnInfo(name = "filmId")
    val filmId: ArrayList<Int>
)

