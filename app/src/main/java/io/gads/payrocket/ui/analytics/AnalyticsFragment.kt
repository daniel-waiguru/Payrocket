package io.gads.payrocket.ui.analytics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import io.gads.payrocket.R

class AnalyticsFragment : Fragment() {

     lateinit var analyticsViewModel: AnalyticsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        analyticsViewModel =
            ViewModelProviders.of(this).get(AnalyticsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_analytics, container, false)
//        val textView: TextView = root.findViewById(R.id.text_analytics)
//        analyticsViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }
}