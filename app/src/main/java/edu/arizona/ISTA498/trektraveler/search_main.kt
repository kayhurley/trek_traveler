package edu.arizona.ISTA498.trektraveler

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button // Import the Button class
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.*
import edu.arizona.ISTA498.trektraveler.CalenderActivity


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

        // Check if the selected date is not null and set the button text
        if (CalenderActivity.selectedDate != null) {
            dateSearchButton.text = CalenderActivity.selectedDate
        }
    }


    fun goToCalendarView(view: View) {
        val intent = Intent(this, CalenderActivity::class.java)
        startActivity(intent)
    }

    fun goToLocationView(view: View) {
        val intent = Intent(this, CityActivity::class.java)
        startActivity(intent)
    }
}
