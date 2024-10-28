package edu.arizona.ISTA498.trektraveler

import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CalenderActivity : AppCompatActivity() {

    // Public variable to hold the selected date
    companion object {
        var selectedDate: Long = 0 // Store the date as milliseconds since epoch
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.cal_screen)

        // Set up window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get references to the CalendarView and buttons
        val calendarView: CalendarView = findViewById(R.id.calendarView)
        val cancelButton: Button = findViewById(R.id.cancelButton)
        val okButton: Button = findViewById(R.id.okayButton)

        // Set the current date as the default selected date
        calendarView.setDate(System.currentTimeMillis(), true, true)

        // Set an OnDateChangeListener to the CalendarView
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            // Save the selected date in milliseconds
            selectedDate = calendarView.date // Get the date in milliseconds
        }

        // Set up Cancel button action
        cancelButton.setOnClickListener {
            // Finish the activity (or handle cancel action as needed)
            finish()
        }

        // Set up OK button action
        okButton.setOnClickListener {
            // Handle OK button action, e.g., pass the selected date to another activity
            // You can also update a button text in another activity if needed
            // Example: Intent to pass the selected date
            // val intent = Intent(this, AnotherActivity::class.java)
            // intent.putExtra("selectedDate", selectedDate)
            // startActivity(intent)
            // For demonstration, you could log the selected date
            println("Selected date: $selectedDate")
            finish() // Optionally finish the activity
        }
    }
}
