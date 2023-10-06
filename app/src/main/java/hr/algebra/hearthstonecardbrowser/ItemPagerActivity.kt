package hr.algebra.hearthstonecardbrowser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import hr.algebra.hearthstonecardbrowser.adapters.ItemPagerAdapter
import hr.algebra.hearthstonecardbrowser.dao.entities.CardEntity
import hr.algebra.hearthstonecardbrowser.databinding.ActivityItemPagerBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


const val POSITION = "hr.algebra.hearthstonecardbrowser.position"

class ItemPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemPagerBinding
    private var itemPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //keep it on light mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding = ActivityItemPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initPager()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun initPager() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val applicationInstance = App.instance
                val dao = applicationInstance.provideDatabase().hearthstoneDao()
                val cards = dao.getAllCards()

                itemPosition = intent.getIntExtra(POSITION, itemPosition)
                binding.viewPager.adapter = ItemPagerAdapter(cards.toMutableList())
                binding.viewPager.currentItem = itemPosition
            }
        }
    }
}