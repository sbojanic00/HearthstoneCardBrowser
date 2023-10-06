package hr.algebra.hearthstonecardbrowser.api

import android.content.Context
import android.util.Log
import hr.algebra.hearthstonecardbrowser.App
import hr.algebra.hearthstonecardbrowser.HearthstoneReceiver
import hr.algebra.hearthstonecardbrowser.dao.entities.CardEntity
import hr.algebra.hearthstonecardbrowser.model.dto.HearthstoneResponseDto
import hr.algebra.hearthstonecardbrowser.utils.framework.sendBroadcast
import hr.algebra.hearthstonecardbrowser.utils.handler.downloadImageAndStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HearthstoneFetcher(private val context: Context) {

    private var hearthstoneApi: HearthstoneApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .build()
        hearthstoneApi = retrofit.create(HearthstoneApi::class.java)
    }

    private fun okHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(HearthstoneInterceptor())
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    fun fetchCards() {
        val request = hearthstoneApi.fetchCards()

        request.enqueue(object : Callback<HearthstoneResponseDto> {
            override fun onResponse(
                call: Call<HearthstoneResponseDto>,
                response: Response<HearthstoneResponseDto>
            ) {
                response.body()?.let { populateItems(it) }
            }

            override fun onFailure(call: Call<HearthstoneResponseDto>, t: Throwable) {
                Log.e(javaClass.name, t.toString(), t)
            }

        })
    }

    private fun populateItems(hearthstoneResponseDto: HearthstoneResponseDto) {

        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                val cardEntities = mutableListOf<CardEntity>()
                hearthstoneResponseDto.forEach { cardDto ->
                    if (!cardDto.img.isNullOrBlank() && !cardDto.description.isNullOrBlank()) {
                        val imgPath = downloadImageAndStore(context, cardDto.img)
                        if (!imgPath.isNullOrBlank()) {
                            val cardEntity = CardEntity(
                                null,
                                cardDto.name,
                                cardDto.description,
                                imgPath
                            )
                            cardEntities.add(cardEntity)
                        }
                    }
                }

                val applicationInstance = App.instance
                val dao = applicationInstance.provideDatabase().hearthstoneDao()
                dao.insertCardEntities(cardEntities)

                context.sendBroadcast<HearthstoneReceiver>()
            }
        }
    }
}
