package hr.algebra.hearthstonecardbrowser

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import hr.algebra.hearthstonecardbrowser.utils.framework.setBooleanPreference
import hr.algebra.hearthstonecardbrowser.utils.framework.startActivity

class HearthstoneReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        context.setBooleanPreference(DATA_IMPORTED)
        context.startActivity<HostActivity>()
    }
}