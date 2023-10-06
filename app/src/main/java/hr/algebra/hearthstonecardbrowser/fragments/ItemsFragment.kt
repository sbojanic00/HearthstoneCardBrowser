package hr.algebra.hearthstonecardbrowser.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import hr.algebra.hearthstonecardbrowser.App
import hr.algebra.hearthstonecardbrowser.adapters.ItemAdapter
import hr.algebra.hearthstonecardbrowser.databinding.FragmentItemsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemsFragment : Fragment() {

    private lateinit var binding: FragmentItemsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val applicationInstance = App.instance
        val dao = applicationInstance.provideDatabase().hearthstoneDao()
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val cards = dao.getAllCards().toMutableList()
                binding.rvItems.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = ItemAdapter(requireContext(), cards)
                }
            }
        }
    }

}