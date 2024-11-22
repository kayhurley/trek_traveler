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

    private var initialDate: String? = null // Stores the initial date

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

        // Set today's date as the minimum date
        val today = Calendar.getInstance()
        calendarView.minDate = today.timeInMillis

        // Store the initial date
        initialDate = selectedDate

        // Check if there's a previously selected date
        if (selectedDate != null) {
            val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
            val date = dateFormat.parse(selectedDate)
            date?.let {
                calendarView.date = it.time // Set calendar to previously selected date
            }
        } else {
            calendarView.date = today.timeInMillis // Default to today’s date if no selected date
        }

        // Set listener to detect date selection and update the selected date
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedCalendar = Calendar.getInstance().apply {
                set(year, month, dayOfMonth)
            }
            val selectedDateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
            selectedDate = selectedDateFormat.format(selectedCalendar.time)
        }

        // Handle Cancel button click
        cancelButton.setOnClickListener {
            // Revert selectedDate to the initial state
            selectedDate = initialDate
            finish() // Finish the activity
        }

        // Handle OK button click
        okButton.setOnClickListener {
            finish() // Finish the activity
        }
    }
}



