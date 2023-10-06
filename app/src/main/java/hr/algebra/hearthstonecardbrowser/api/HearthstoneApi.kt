package hr.algebra.hearthstonecardbrowser.api

import hr.algebra.hearthstonecardbrowser.model.dto.HearthstoneResponseDto
import retrofit2.Call
import retrofit2.http.GET


const val API_URL = "https://omgvamp-hearthstone-v1.p.rapidapi.com/"
//@Query("set")set: String
interface HearthstoneApi {
    @GET("cardbacks")
    fun fetchCards(): Call<HearthstoneResponseDto>
}