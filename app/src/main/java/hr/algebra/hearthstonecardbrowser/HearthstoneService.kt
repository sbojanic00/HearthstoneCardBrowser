package hr.algebra.hearthstonecardbrowser

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import hr.algebra.hearthstonecardbrowser.api.HearthstoneFetcher

private const val JOB_ID = 1
class HearthstoneService : JobIntentService() {
    override fun onHandleWork(intent: Intent) {
        HearthstoneFetcher(this).fetchCards()
    }


    companion object {
        fun enqueue(context: Context) {
            enqueueWork(
                context, HearthstoneService::class.java, JOB_ID,
                Intent(context, HearthstoneService::class.java)
            )
        }
    }
}