package edu.arizona.ISTA498.trektraveler

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.widget.TextView
import androidx.core.content.ContextCompat

class locationSearch : AppCompatActivity() {

    companion object {
        var selectedLocation: String? = null // Static variable to hold the selected location
    }

    private lateinit var searchView: SearchView
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>

    // Predefined list of locations
    private val locations = listOf("Tucson, AZ")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_search)

        // Setup toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Show back button in toolbar

        // Initialize the SearchView and ListView
        searchView = findViewById(R.id.search)
        listView = findViewById(R.id.list_view)


        // Initialize the ArrayAdapter with an empty list
        adapter = ArrayAdapter(this, R.layout.list_item, mutableListOf())
        listView.adapter = adapter

        // Set up a TextWatcher to filter the ListView based on user input
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Clear previous results
                adapter.clear()

                // Filter the predefined locations based on user input
                if (!newText.isNullOrEmpty()) {
                    // Check for matches in the predefined list
                    for (location in locations) {
                        if (location.contains(newText, ignoreCase = true)) {
                            adapter.add(location) // Add matching locations to the adapter
                        }
                    }
                }

                adapter.notifyDataSetChanged() // Notify adapter of changes
                return true
            }
        })

        // Handle item clicks in the ListView
        listView.setOnItemClickListener { _, _, position, _ ->
            val selected = adapter.getItem(position)
            if (selected != null) {
                onLocationSelected(selected) // Update the selected location
            }
        }
    }

    // Set selectedLocation when the user confirms their selection
    private fun onLocationSelected(location: String) {
        selectedLocation = location
        finish() // Close the activity and return
    }
}
