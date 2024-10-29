package edu.arizona.ISTA498.trektraveler

import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import java.text.SimpleDateFormat
import java.util.*


class CalenderActivity : AppCompatActivity() {

    companion object {
        var selectedDate: String? = null
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

        // Set the CalendarView to show today's date
        calendarView.setDate(System.currentTimeMillis(), false, true)

        // Listener for when the user selects a different date
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            // Create a calendar instance to format the selected date
            val calendar = Calendar.getInstance().apply {
                set(year, month, dayOfMonth)
            }
            // Format the selected date and update the companion object variable
            val selectedDateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
            selectedDate = selectedDateFormat.format(calendar.time)
        }

        cancelButton.setOnClickListener {
            finish() // Finish the activity
        }

        // Set up the OK button click listener
        okButton.setOnClickListener {
            // Optionally update the selected date again on OK click
            // The selected date is already updated when the user picks a date from the calendar
            finish() // Finish the activity
        }
    }
}


