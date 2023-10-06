package hr.algebra.hearthstonecardbrowser.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hr.algebra.hearthstonecardbrowser.dao.entities.CardEntity

@Dao
interface HearthstoneDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCardEntities(cardEntities: List<CardEntity>)

    @Query("SELECT * FROM CardEntity")
    fun getAllCards(): List<CardEntity>

}