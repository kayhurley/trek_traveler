package edu.arizona.ISTA498.trektraveler

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize the progress bar and bike image views
        val progressBar = findViewById<View>(R.id.progress_bar) as ProgressBar
        val bikeImageView = findViewById<View>(R.id.bikeImageView)

        // Animate the progress bar from 0 to 100 over 2 seconds
        val progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100)
        progressAnimator.duration = 4000 // 2 seconds

        // Animate the bike image to move along with the progress
        val bikeAnimator = ObjectAnimator.ofFloat(bikeImageView, "translationX", 0f, 800f)
        bikeAnimator.duration = 4000 // 2 seconds

        // Start both animations
        progressAnimator.start()
        bikeAnimator.start()

        // Delay for 2 seconds, then start the main activity
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, SearchMain::class.java))
            finish() // Close Splash activity
        }, 4000) // 2-second delay to match animation duration

    }
}