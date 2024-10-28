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


        cancelButton.setOnClickListener {
            finish() // Finish the activity
        }
        // Set up the OK button click listener
        okButton.setOnClickListener {
            // Get the selected date from the CalendarView
            val selectedMillis = calendarView.date
            val selectedDateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
            selectedDate = selectedDateFormat.format(Date(selectedMillis)) // Format the date

            finish() // Finish the activity
        }


    }

}
