package edu.arizona.ISTA498.trektraveler

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton

class InfoTab : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.info_screen, container, false)

        // Set up the MaterialButton click listener
        val walkscoreLink = view.findViewById<MaterialButton>(R.id.walkscoreLink)
        walkscoreLink.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.walkscore.com/methodology.shtml"))
            startActivity(intent)
        }

        return view
    }

}