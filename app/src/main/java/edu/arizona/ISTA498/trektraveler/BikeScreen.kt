package edu.arizona.ISTA498.trektraveler

import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.google.android.material.appbar.MaterialToolbar
import org.json.JSONArray

class BikeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.bike_screen)

        // Adjust view to fit system bars (status bar, navigation bar)
        val mainView = findViewById<View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updatePadding(top = systemBars.top, bottom = systemBars.bottom)
            insets
        }

        // Set up the top app bar with a back button listener
        val topAppBar = findViewById<MaterialToolbar>(R.id.BikeAppBar)
        topAppBar.setNavigationOnClickListener {
            onBackPressed() // Go back to the previous screen
        }

        val bikeListView = findViewById<ListView>(R.id.bikeListView)

        val bikeShops = getBikeShopsFromJson()
        val adapter = BikeAdapter(this, bikeShops)

        bikeListView.adapter = adapter
    }

    private fun getBikeShopsFromJson(): List<BikeShop> {
        val JSON_string = """[
            {
                "business_status": "OPERATIONAL",
                "geometry": {
                    "location": {
                        "lat": 32.157255,
                        "lng": -111.022411
                    }
                },
                "name": "Winston Reynolds - Manzanita Park",
                "vicinity": "5200 S Westover Ave, Tucson"
            },
            {
                "business_status": "OPERATIONAL",
                "geometry": {
                    "location": {
                        "lat": 32.1784054,
                        "lng": -110.9514553
                    }
                },
                "name": "Ajo Bikes",
                "vicinity": "1301 E Ajo Way #117, Tucson"
            },
            {
                "business_status": "OPERATIONAL",
                "geometry": {
                    "location": {
                        "lat": 32.1777929,
                        "lng": -110.8406468
                    }
                },
                "name": "Tucson Adventure Rentals",
                "vicinity": "3797 S Kolb Rd, Tucson"
            }
        ]"""

        val bikeShops = mutableListOf<BikeShop>()
        val jsonArray = JSONArray(JSON_string)

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val geometry = jsonObject.getJSONObject("geometry").getJSONObject("location")
            val latitude = geometry.getDouble("lat")
            val longitude = geometry.getDouble("lng")
            val name = jsonObject.getString("name")
            val vicinity = jsonObject.getString("vicinity")

            bikeShops.add(BikeShop(latitude, longitude, name, vicinity))
        }

        return bikeShops
    }
}