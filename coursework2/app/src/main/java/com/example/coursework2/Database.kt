import androidx.room.*
import androidx.room.Database
import com.example.coursework2.PhotoTable
import com.example.coursework2.PhotoTableDao


@Database(entities = [PhotoTable::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun tableDao(): PhotoTableDao
}