package com.example.effectivemobiletestapp.ui.screens.empty_placeholder

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.effectivemobiletestapp.R
import com.example.effectivemobiletestapp.ui.MainActivity


class EmptyPlaceholderFragment : Fragment(R.layout.fragment_empty_placeholder) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as MainActivity).let { activity ->
            activity.findViewById<Button>(R.id.back_button).setOnClickListener {
                activity.popBackStack()
            }
        }
    }
}