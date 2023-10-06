package hr.algebra.hearthstonecardbrowser.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hr.algebra.hearthstonecardbrowser.R
import hr.algebra.hearthstonecardbrowser.databinding.FragmentAboutBinding
import hr.algebra.hearthstonecardbrowser.utils.framework.applyAnimation

class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvAbout.applyAnimation(R.anim.rotate)
    }
}