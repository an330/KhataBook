import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.khatabook.RoomDao
import com.example.khatabook.User
import com.example.khatabook.UserDao
import com.example.khatabook.roomEntity

@Database(entities = [User::class, roomEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun itemDao(): RoomDao
}
