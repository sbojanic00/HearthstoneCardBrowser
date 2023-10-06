package hr.algebra.hearthstonecardbrowser

import android.app.Application

class App : Application() {

    val application: HearthstoneApp
    get() = instance

    override fun onCreate() {
        super.onCreate()
        instance = HearthstoneApp(this)
    }

    companion object {
        lateinit var instance: HearthstoneApp
            private set
    }
}