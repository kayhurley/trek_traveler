package edu.arizona.ISTA498.trektraveler

import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*


class CalenderActivity : AppCompatActivity() {

    companion object {
        var selectedDate: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.cal_screen)

        val calendarView: CalendarView = findViewById(R.id.calendarView)
        val todayButton: ImageView = findViewById(R.id.TodayButton)
        val todayDate: TextView = findViewById(R.id.todayDate) // Reference to the TextView
        val cancelButton: Button = findViewById(R.id.cancelButton)
        val okButton: Button = findViewById(R.id.okayButton)

        // Get today's date
        val today = Calendar.getInstance()
        val todayDay = today.get(Calendar.DAY_OF_MONTH) // Extract today's day

        // Set the TextView to display today's day
        todayDate.text = todayDay.toString()

        // Set the minimum date for the calendar
        calendarView.minDate = today.timeInMillis

        // Preselect today's date or restore previously selected date
        val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        if (selectedDate != null) {
            val date = dateFormat.parse(selectedDate!!)
            date?.let {
                calendarView.date = it.time
            }
        } else {
            calendarView.date = today.timeInMillis
        }

        // Set listener for date selection
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedCalendar = Calendar.getInstance().apply {
                set(year, month, dayOfMonth)
            }
            selectedDate = dateFormat.format(selectedCalendar.time)
        }

        // Handle Today button click
        todayButton.setOnClickListener {
            calendarView.date = today.timeInMillis // Reset CalendarView to today's date
            selectedDate = dateFormat.format(today.time) // Update selectedDate
        }

        // Handle Cancel button click
        cancelButton.setOnClickListener {
            selectedDate = null
            finish()
        }

        // Handle OK button click
        okButton.setOnClickListener {
            if (selectedDate == null) {
                selectedDate = dateFormat.format(today.time) // Use today's date as default
            }

            finish()
        }
    }
}



