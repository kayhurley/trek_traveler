package edu.arizona.ISTA498.trektraveler

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView // Import the TextView class
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SearchMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search_main)

        // Set up window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onResume() {
        super.onResume()

        // Get a reference to the button
        val dateSearchButton: Button = findViewById(R.id.dateSearch)
        val locationButton: Button = findViewById(R.id.locationSearch) // Assume you have a button for the location search

        // Check if the selected date is not null and set the button text
        if (CalenderActivity.selectedDate != null) {
            dateSearchButton.text = CalenderActivity.selectedDate
        }

        // Check if the selected location is not null and update the location button text
        if (locationSearch.selectedLocation != null) {
            locationButton.text = locationSearch.selectedLocation
        }
    }

    fun goToCalendarView(view: View) {
        val intent = Intent(this, CalenderActivity::class.java)
        startActivity(intent)
    }

    fun goToLocationView(view: View) {
        val intent = Intent(this, locationSearch::class.java)
        startActivity(intent)
    }
}
