package edu.arizona.ISTA498.trektraveler

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class PlacesTab : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.places_screen, container, false)

        // Set an onClickListener for the button programmatically
        val goToBikeButton: View = view.findViewById(R.id.goToBikeButton)
        goToBikeButton.setOnClickListener {
            goToBikeScreen()
        }

        val gotoTransitButton: View = view.findViewById(R.id.goToTransitButton)
        gotoTransitButton.setOnClickListener {
            gotToTransitScreen()
        }

        return view
    }

    private fun goToBikeScreen() {
        // Use requireContext() to get the context within a Fragment
        val intent = Intent(requireContext(), BikeScreen::class.java)
        startActivity(intent)
    }

    private fun gotToTransitScreen() {
        // Use requireContext() to get the context within a Fragment
        val intent = Intent(requireContext(), TransitScreen::class.java)
        startActivity(intent)
    }
}

