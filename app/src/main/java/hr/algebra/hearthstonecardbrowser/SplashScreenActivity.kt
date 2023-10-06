package hr.algebra.hearthstonecardbrowser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import hr.algebra.hearthstonecardbrowser.R.string
import hr.algebra.hearthstonecardbrowser.databinding.ActivitySplashScreenBinding
import hr.algebra.hearthstonecardbrowser.utils.framework.applyAnimation
import hr.algebra.hearthstonecardbrowser.utils.framework.callDelayed
import hr.algebra.hearthstonecardbrowser.utils.framework.getBooleanPreference
import hr.algebra.hearthstonecardbrowser.utils.framework.isOnline
import hr.algebra.hearthstonecardbrowser.utils.framework.startActivity

private const val DELAY = 3000L
const val DATA_IMPORTED = "hr.algebra.hearthstonecardbrowser.data_imported"

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //keep it on light mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startAnimations()
        redirect()
    }

    private fun startAnimations() {
        binding.ivSplash.applyAnimation(R.anim.zoom_and_fade_in)
        binding.tvSplash.applyAnimation(R.anim.blink)
    }

    private fun redirect() {

        if (getBooleanPreference(DATA_IMPORTED)) {
            callDelayed(DELAY) { startActivity<HostActivity>()}

        } else {
            if (isOnline()) {
                HearthstoneService.enqueue(this)
            } else {
                binding.tvSplash.text = getString(R.string.no_connection)
                callDelayed(DELAY) { finish() }
            }
        }
    }
}