package com.example.effectivemobiletestapp.ui.screens.shortly

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.effectivemobiletestapp.R
import com.example.effectivemobiletestapp.databinding.FragmentShortlyBinding

class ShortlyFragment : Fragment(R.layout.fragment_shortly) {

    private var _binding: FragmentShortlyBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel by lazy { ViewModelProvider(this).get(ShortlyViewModel::class.java) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentShortlyBinding.bind(view)

        val textView: TextView = binding.textNotifications
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}