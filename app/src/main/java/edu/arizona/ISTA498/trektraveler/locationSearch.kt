package edu.arizona.ISTA498.trektraveler

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.MaterialToolbar

class locationSearch : AppCompatActivity() {
    companion object {
        var selectedLocation: String? = null
    }

    private lateinit var searchView: SearchView
    private lateinit var listView: ListView
    private lateinit var noResultsContainer: LinearLayout
    private lateinit var adapter: ArrayAdapter<String>

    private val locations = listOf("Tucson, AZ")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_search)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        searchView = findViewById(R.id.search)
        listView = findViewById(R.id.list_view)
        noResultsContainer = findViewById(R.id.no_results_container)

        adapter = ArrayAdapter(this, R.layout.list_item, locations.toMutableList())
        listView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.clear()
                if (!newText.isNullOrEmpty()) {
                    val filteredLocations = locations.filter {
                        it.contains(newText, ignoreCase = true)
                    }
                    adapter.addAll(filteredLocations)
                    toggleNoResults(filteredLocations.isEmpty())
                } else {
                    adapter.addAll(locations)
                    toggleNoResults(false)
                }
                adapter.notifyDataSetChanged()
                return true
            }
        })

        listView.setOnItemClickListener { _, _, position, _ ->
            val selected = adapter.getItem(position)
            if (selected != null) {
                onLocationSelected(selected)
            }
        }
    }

    private fun toggleNoResults(show: Boolean) {
        noResultsContainer.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun onLocationSelected(location: String) {
        selectedLocation = location
        finish()
    }
}
