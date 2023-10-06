package hr.algebra.hearthstonecardbrowser.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hr.algebra.hearthstonecardbrowser.dao.entities.CardEntity

@Database(
    entities = [CardEntity::class],
    version = 1,
    exportSchema = false
)

abstract class CardsDatabase : RoomDatabase() {
    abstract fun hearthstoneDao() : HearthstoneDao

    companion object {
        @Volatile
        private var INSTANCE: CardsDatabase? = null

        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(CardsDatabase::class.java) {
                INSTANCE ?: buildDatabase(context)?.also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CardsDatabase::class.java,
            "cards.db"
        ).build()


    }
}