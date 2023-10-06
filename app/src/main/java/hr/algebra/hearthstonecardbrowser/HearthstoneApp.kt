package hr.algebra.hearthstonecardbrowser

import hr.algebra.hearthstonecardbrowser.dao.CardsDatabase

class HearthstoneApp(val native: App) {

    fun provideDatabase() = CardsDatabase.getInstance(native)

}