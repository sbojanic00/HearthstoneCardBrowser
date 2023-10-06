package hr.algebra.hearthstonecardbrowser.model.dto


import com.google.gson.annotations.SerializedName

data class HearthstoneResponseDtoItem(
    @SerializedName("cardBackId")
    val cardBackId: Int,
    @SerializedName("description")
    val description: String?,
    @SerializedName("enabled")
    val enabled: Boolean,
    @SerializedName("img")
    val img: String?,
    @SerializedName("imgAnimated")
    val imgAnimated: String?,
    @SerializedName("locale")
    val locale: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("sortCategory")
    val sortCategory: Int,
    @SerializedName("sortOrder")
    val sortOrder: Int,
    @SerializedName("source")
    val source: String
)