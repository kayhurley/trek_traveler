package edu.arizona.ISTA498.trektraveler

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class PlacesTab : Fragment() {
    private val allTransitShops = mutableListOf<TransitShop>() // List to hold all transit stops

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.places_screen, container, false)

        // Set up button listeners for navigation
        val goToBikeButton: View = view.findViewById(R.id.goToBikeButton)
        goToBikeButton.setOnClickListener {
            goToBikeScreen()
        }

        val gotoTransitButton: View = view.findViewById(R.id.goToTransitButton)
        gotoTransitButton.setOnClickListener {
            goToTransitScreen()
        }

        // Set up TextView listener for navigating to NeighborhoodsActivity
        val goToNeighborhood: View = view.findViewById(R.id.goToNeighborhood)
        goToNeighborhood.setOnClickListener {
            goToNeighborhoodScreen()
        }


        // Set up button listeners for transit stop locations
        val directionsButton1: Button = view.findViewById(R.id.directionsButton) // Los Reales/Cardinal
        directionsButton1.setOnClickListener {
            val name = "Los Reales/Cardinal"
            openGoogleMaps(name)
        }

//        val directionsButton2: Button = view.findViewById(R.id.directionsButton2) // Cardinal/Pajarito
//        directionsButton2.setOnClickListener {
//            val name = "Cardinal/Pajarito"
//            openGoogleMaps(name)
//        }

        val directionsBikeButton1: Button = view.findViewById(R.id.bikeRentalDirectionsButton1)
        directionsBikeButton1.setOnClickListener {
            val name = "Winston Reynolds - Manzanita Park"
            openGoogleMaps(name)
        }

//        val directionsBikeButton2: Button = view.findViewById(R.id.bikeRentalDirectionsButton2)
//        directionsBikeButton2.setOnClickListener {
//            val name = "Ajo Bikes"
//            openGoogleMaps(name)
//        }

        return view
    }

    // Navigate to the BikeScreen
    private fun goToBikeScreen() {
        val intent = Intent(requireContext(), BikeScreen::class.java)
        startActivity(intent)
    }

    // Navigate to the TransitScreen
    private fun goToTransitScreen() {
        val intent = Intent(requireContext(), TransitScreen::class.java)
        startActivity(intent)
    }

    private fun goToNeighborhoodScreen() {
        val intent = Intent(requireContext(), neighorhoods::class.java)
        startActivity(intent)
    }

    // Function to open Google Maps with the name and coordinates
    private fun openGoogleMaps(name: String) {
        // Updated URI format to initiate a search for the place name in Google Maps
        val uri = Uri.parse("geo:0,0?q=${Uri.encode("$name, Tucson")}")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.google.android.apps.maps") // Ensures Google Maps is used
        startActivity(intent)
    }
}

