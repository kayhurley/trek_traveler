package edu.arizona.ISTA498.trektraveler

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HotelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.hotel)

        // Set up the app bar for back navigation
        val toolbar = findViewById<Toolbar>(R.id.toolbar) // Ensure your layout has a Toolbar with this ID
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Handle the back button click in the app bar
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Set window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Add click listener to the hotelInfoButton
        val hotelInfoButton = findViewById<Button>(R.id.hotelInfoButton)
        hotelInfoButton.setOnClickListener {
            val intent = Intent(this, HotelInfo::class.java)
            startActivity(intent)
        }
        // Add click listener to the hotelInfo2Button
        val hotelInfoButton2 = findViewById<Button>(R.id.hotel2InfoButton)
        hotelInfoButton2.setOnClickListener {
            val intent = Intent(this, HotelInfo2::class.java)
            startActivity(intent)
        }
        // Add click listener to the hotelInfo3Button
        val hotelInfoButton3 = findViewById<Button>(R.id.hotel3InfoButton)
        hotelInfoButton3.setOnClickListener {
            val intent = Intent(this, HotelInfo3::class.java)
            startActivity(intent)
        }
    }
}