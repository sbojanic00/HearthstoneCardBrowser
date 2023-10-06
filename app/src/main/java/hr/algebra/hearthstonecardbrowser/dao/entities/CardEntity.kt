package hr.algebra.hearthstonecardbrowser.dao.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CardEntity (
    @PrimaryKey(autoGenerate = true)
    val _id: Long? = null,
    val name: String,
    val description: String,
    val imgPath: String
)