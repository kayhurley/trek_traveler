package edu.arizona.ISTA498.trektraveler

import TransitAdapter
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar
import org.json.JSONArray
import org.json.JSONException

class TransitScreen : AppCompatActivity() {

    private val allTransitShops = mutableListOf<TransitShop>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.public_transit_screen)

        // Edge-to-edge system bar insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set up back navigation in the app bar
        val topAppBar = findViewById<MaterialToolbar>(R.id.TransitAppBar)
        topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }

        // Fetch transit stops and set up ListView
        get_google_maps_transit_stops()
        setupListView()
    }

    private fun get_google_maps_transit_stops() {
        try {
            val JSON_string = """[
                {
                    "business_status": "OPERATIONAL",
                    "geometry": {
                        "location": {
                            "lat": 32.1195015,
                            "lng": -111.0291265
                        },
                        "viewport": {
                            "northeast": {
                                "lat": 32.1208507302915,
                                "lng": -111.0277090197085
                            },
                            "southwest": {
                                "lat": 32.1181527697085,
                                "lng": -111.0304069802915
                            }
                        }
                    },
                    "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
                    "icon_background_color": "#7B9EB0",
                    "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
                    "name": "Los Reales/Cardinal",
                    "place_id": "ChIJc4iZvQ551oYR5mlUcBXv71k",
                    "plus_code": {
                        "compound_code": "4X9C+R8 Drexel Heights, AZ, USA",
                        "global_code": "854C4X9C+R8"
                    },
                    "reference": "ChIJc4iZvQ551oYR5mlUcBXv71k",
                    "scope": "GOOGLE",
                    "types": [
                        "transit_station",
                        "point_of_interest",
                        "establishment"
                    ],
                    "vicinity": "United States"
                },
                {
                    "business_status": "OPERATIONAL",
                    "geometry": {
                        "location": {
                            "lat": 32.12774999999999,
                            "lng": -111.028967
                        },
                        "viewport": {
                            "northeast": {
                                "lat": 32.1290989302915,
                                "lng": -111.0276249197085
                            },
                            "southwest": {
                                "lat": 32.1264009697085,
                                "lng": -111.0303228802915
                            }
                        }
                    },
                    "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
                    "icon_background_color": "#7B9EB0",
                    "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
                    "name": "Cardinal/Pajarito",
                    "place_id": "ChIJq-czOKd51oYREKLN-r98NhA",
                    "plus_code": {
                        "compound_code": "4XHC+3C Drexel Heights, AZ, USA",
                        "global_code": "854C4XHC+3C"
                    },
                    "reference": "ChIJq-czOKd51oYREKLN-r98NhA",
                    "scope": "GOOGLE",
                    "types": [
                        "transit_station",
                        "point_of_interest",
                        "establishment"
                    ],
                    "vicinity": "United States"
                },
                {
                    "business_status": "OPERATIONAL",
                    "geometry": {
                        "location": {
                            "lat": 32.1191565,
                            "lng": -111.033536
                        },
                        "viewport": {
                            "northeast": {
                                "lat": 32.1205004802915,
                                "lng": -111.0321869197085
                            },
                            "southwest": {
                                "lat": 32.1178025197085,
                                "lng": -111.0348848802915
                            }
                        }
                    },
                    "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
                    "icon_background_color": "#7B9EB0",
                    "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
                    "name": "Los Reales/Cmo Alegre",
                    "place_id": "ChIJ2x8EWw951oYRtZ-47Jd93GM",
                    "plus_code": {
                        "compound_code": "4X98+MH Drexel Heights, AZ, USA",
                        "global_code": "854C4X98+MH"
                    },
                    "reference": "ChIJ2x8EWw951oYRtZ-47Jd93GM",
                    "scope": "GOOGLE",
                    "types": [
                        "transit_station",
                        "point_of_interest",
                        "establishment"
                    ],
                    "vicinity": "United States"
                },
                {
                    "business_status": "OPERATIONAL",
                    "geometry": {
                        "location": {
                            "lat": 32.119748,
                            "lng": -111.028858
                        },
                        "viewport": {
                            "northeast": {
                                "lat": 32.1210434802915,
                                "lng": -111.0275091197085
                            },
                            "southwest": {
                                "lat": 32.11834551970851,
                                "lng": -111.0302070802915
                            }
                        }
                    },
                    "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
                    "icon_background_color": "#7B9EB0",
                    "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
                    "name": "Cardinal/Los Reales (NE)",
                    "place_id": "ChIJ8XxIow551oYR8Mv5yRUUP5A",
                    "plus_code": {
                        "compound_code": "4X9C+VF Drexel Heights, AZ, USA",
                        "global_code": "854C4X9C+VF"
                    },
                    "reference": "ChIJ8XxIow551oYR8Mv5yRUUP5A",
                    "scope": "GOOGLE",
                    "types": [
                        "transit_station",
                        "point_of_interest",
                        "establishment"
                    ],
                    "vicinity": "United States"
                },
                {
                    "business_status": "OPERATIONAL",
                    "geometry": {
                        "location": {
                            "lat": 32.124141,
                            "lng": -111.029046
                        },
                        "viewport": {
                            "northeast": {
                                "lat": 32.1254899802915,
                                "lng": -111.0276970197085
                            },
                            "southwest": {
                                "lat": 32.1227920197085,
                                "lng": -111.0303949802915
                            }
                        }
                    },
                    "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
                    "icon_background_color": "#7B9EB0",
                    "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
                    "name": "Cardinal/Christopher Dr (SW)",
                    "place_id": "ChIJDTqOmgl51oYRYRbUfpmMDmM",
                    "plus_code": {
                        "compound_code": "4XFC+M9 Drexel Heights, AZ, USA",
                        "global_code": "854C4XFC+M9"
                    },
                    "reference": "ChIJDTqOmgl51oYRYRbUfpmMDmM",
                    "scope": "GOOGLE",
                    "types": [
                        "transit_station",
                        "point_of_interest",
                        "establishment"
                    ],
                    "vicinity": "United States"
                }
            ]"""  // Full original JSON here

            val JSON_response = JSONArray(JSON_string)

            for (i in 0 until JSON_response.length()) {
                val businessObject = JSON_response.getJSONObject(i)
                val geometryObject = businessObject.getJSONObject("geometry")
                val locationObject = geometryObject.getJSONObject("location")

                val latitude = locationObject.getDouble("lat")
                val longitude = locationObject.getDouble("lng")
                val name = businessObject.getString("name")
                val vicinity = businessObject.getString("vicinity")

                // Create a TransitShop object and add it to the list
                val transitStop = TransitShop(latitude, longitude, name, vicinity)
                allTransitShops.add(transitStop)
            }

        } catch (e: JSONException) {
            e.printStackTrace()  // Log any JSON parsing errors
        }
    }

    private fun setupListView() {
        val listView = findViewById<ListView>(R.id.listView)
        val adapter = TransitAdapter(this, allTransitShops)
        listView.adapter = adapter
    }
}