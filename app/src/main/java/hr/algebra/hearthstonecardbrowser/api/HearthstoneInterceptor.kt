package hr.algebra.hearthstonecardbrowser.api

import okhttp3.Interceptor
import okhttp3.Response

class HearthstoneInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("X-RapidAPI-Key", "1a6c5914f9msh692d54b20ecbde5p11c875jsn4ee03f18cef4")
        requestBuilder.addHeader("X-RapidAPI-Host", "omgvamp-hearthstone-v1.p.rapidapi.com")
        return chain.proceed(requestBuilder.build())
    }
}