package edu.arizona.ISTA498.trektraveler
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {
    var allBikeShops = ArrayList<BikeShop>()
    var allTransitShops = ArrayList<TransitShop>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun get_walk_score_data() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response =
                    URL("https://api.walkscore.com/score?format=json&lat=32.2226&lon=-110.9747&transit=1&bike=1&wsapikey=d874b2705cbb91f53a74fd1003d74fdf").readText()
                val jsonresponse = parseObject(response)
                val walk_score = jsonresponse?.getInt("walkscore")
                val transit = jsonresponse?.getJSONObject("transit")
                val transit_score = transit?.getInt("score")
                val bikes = jsonresponse?.getJSONObject("bike")
                val bike_score = bikes?.getInt("score")
                print("Walk Score is: " + walk_score)
                print("Transit Score is: " + transit_score)
                print("Bike Score is: " + bike_score)
            } catch (e: java.lang.Exception) {
                print(e.message)
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun get_weather_score_data() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response =
                    URL("https://pro.openweathermap.org/data/2.5/weather?q=Arizona,us&APPID=772a8fce0fd1f9e51c34f53b07845329&units=imperial").readText()
                val jsonresponse = parseObject(response)
                val weather = jsonresponse?.getJSONArray("weather")!!
                if (weather.length() > 0) {
                    val weather_object = weather[0] as JSONObject
                    val weather_main = weather_object.getString("main")
                    val weather_description = weather_object.getString("description")
                    val weather_icon = weather_object.getString("icon")
                }
                val main_object = jsonresponse.getJSONObject("main")
                val temp_min = main_object.getDouble("temp_min")
                val temp_max = main_object.getDouble("temp_max")
                print("Max Temperature is: " + temp_max)
                print("Min Temperature is: " + temp_min)
            } catch (e: java.lang.Exception) {
                print(e.message)
            }

        }

    }

    fun get_google_maps_transit_stops() {
        try {
            val JSON_string = "[\n" +
                    "    {\n" +
                    "        \"business_status\": \"OPERATIONAL\",\n" +
                    "        \"geometry\": {\n" +
                    "            \"location\": {\n" +
                    "                \"lat\": 32.1195015,\n" +
                    "                \"lng\": -111.0291265\n" +
                    "            },\n" +
                    "            \"viewport\": {\n" +
                    "                \"northeast\": {\n" +
                    "                    \"lat\": 32.1208507302915,\n" +
                    "                    \"lng\": -111.0277090197085\n" +
                    "                },\n" +
                    "                \"southwest\": {\n" +
                    "                    \"lat\": 32.1181527697085,\n" +
                    "                    \"lng\": -111.0304069802915\n" +
                    "                }\n" +
                    "            }\n" +
                    "        },\n" +
                    "        \"icon\": \"https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png\",\n" +
                    "        \"icon_background_color\": \"#7B9EB0\",\n" +
                    "        \"icon_mask_base_uri\": \"https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet\",\n" +
                    "        \"name\": \"Los Reales/Cardinal\",\n" +
                    "        \"place_id\": \"ChIJc4iZvQ551oYR5mlUcBXv71k\",\n" +
                    "        \"plus_code\": {\n" +
                    "            \"compound_code\": \"4X9C+R8 Drexel Heights, AZ, USA\",\n" +
                    "            \"global_code\": \"854C4X9C+R8\"\n" +
                    "        },\n" +
                    "        \"reference\": \"ChIJc4iZvQ551oYR5mlUcBXv71k\",\n" +
                    "        \"scope\": \"GOOGLE\",\n" +
                    "        \"types\": [\n" +
                    "            \"transit_station\",\n" +
                    "            \"point_of_interest\",\n" +
                    "            \"establishment\"\n" +
                    "        ],\n" +
                    "        \"vicinity\": \"United States\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"business_status\": \"OPERATIONAL\",\n" +
                    "        \"geometry\": {\n" +
                    "            \"location\": {\n" +
                    "                \"lat\": 32.12774999999999,\n" +
                    "                \"lng\": -111.028967\n" +
                    "            },\n" +
                    "            \"viewport\": {\n" +
                    "                \"northeast\": {\n" +
                    "                    \"lat\": 32.1290989302915,\n" +
                    "                    \"lng\": -111.0276249197085\n" +
                    "                },\n" +
                    "                \"southwest\": {\n" +
                    "                    \"lat\": 32.1264009697085,\n" +
                    "                    \"lng\": -111.0303228802915\n" +
                    "                }\n" +
                    "            }\n" +
                    "        },\n" +
                    "        \"icon\": \"https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png\",\n" +
                    "        \"icon_background_color\": \"#7B9EB0\",\n" +
                    "        \"icon_mask_base_uri\": \"https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet\",\n" +
                    "        \"name\": \"Cardinal/Pajarito\",\n" +
                    "        \"place_id\": \"ChIJq-czOKd51oYREKLN-r98NhA\",\n" +
                    "        \"plus_code\": {\n" +
                    "            \"compound_code\": \"4XHC+3C Drexel Heights, AZ, USA\",\n" +
                    "            \"global_code\": \"854C4XHC+3C\"\n" +
                    "        },\n" +
                    "        \"reference\": \"ChIJq-czOKd51oYREKLN-r98NhA\",\n" +
                    "        \"scope\": \"GOOGLE\",\n" +
                    "        \"types\": [\n" +
                    "            \"transit_station\",\n" +
                    "            \"point_of_interest\",\n" +
                    "            \"establishment\"\n" +
                    "        ],\n" +
                    "        \"vicinity\": \"United States\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"business_status\": \"OPERATIONAL\",\n" +
                    "        \"geometry\": {\n" +
                    "            \"location\": {\n" +
                    "                \"lat\": 32.1191565,\n" +
                    "                \"lng\": -111.033536\n" +
                    "            },\n" +
                    "            \"viewport\": {\n" +
                    "                \"northeast\": {\n" +
                    "                    \"lat\": 32.1205004802915,\n" +
                    "                    \"lng\": -111.0321869197085\n" +
                    "                },\n" +
                    "                \"southwest\": {\n" +
                    "                    \"lat\": 32.1178025197085,\n" +
                    "                    \"lng\": -111.0348848802915\n" +
                    "                }\n" +
                    "            }\n" +
                    "        },\n" +
                    "        \"icon\": \"https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png\",\n" +
                    "        \"icon_background_color\": \"#7B9EB0\",\n" +
                    "        \"icon_mask_base_uri\": \"https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet\",\n" +
                    "        \"name\": \"Los Reales/Cmo Alegre\",\n" +
                    "        \"place_id\": \"ChIJ2x8EWw951oYRtZ-47Jd93GM\",\n" +
                    "        \"plus_code\": {\n" +
                    "            \"compound_code\": \"4X98+MH Drexel Heights, AZ, USA\",\n" +
                    "            \"global_code\": \"854C4X98+MH\"\n" +
                    "        },\n" +
                    "        \"reference\": \"ChIJ2x8EWw951oYRtZ-47Jd93GM\",\n" +
                    "        \"scope\": \"GOOGLE\",\n" +
                    "        \"types\": [\n" +
                    "            \"transit_station\",\n" +
                    "            \"point_of_interest\",\n" +
                    "            \"establishment\"\n" +
                    "        ],\n" +
                    "        \"vicinity\": \"United States\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"business_status\": \"OPERATIONAL\",\n" +
                    "        \"geometry\": {\n" +
                    "            \"location\": {\n" +
                    "                \"lat\": 32.119748,\n" +
                    "                \"lng\": -111.028858\n" +
                    "            },\n" +
                    "            \"viewport\": {\n" +
                    "                \"northeast\": {\n" +
                    "                    \"lat\": 32.1210434802915,\n" +
                    "                    \"lng\": -111.0275091197085\n" +
                    "                },\n" +
                    "                \"southwest\": {\n" +
                    "                    \"lat\": 32.11834551970851,\n" +
                    "                    \"lng\": -111.0302070802915\n" +
                    "                }\n" +
                    "            }\n" +
                    "        },\n" +
                    "        \"icon\": \"https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png\",\n" +
                    "        \"icon_background_color\": \"#7B9EB0\",\n" +
                    "        \"icon_mask_base_uri\": \"https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet\",\n" +
                    "        \"name\": \"Cardinal/Los Reales (NE)\",\n" +
                    "        \"photos\": [\n" +
                    "            {\n" +
                    "                \"height\": 4160,\n" +
                    "                \"html_attributions\": [\n" +
                    "                    \"<a href=\\\"https://maps.google.com/maps/contrib/109781904960670385593\\\">Luis M Gaxiola S</a>\"\n" +
                    "                ],\n" +
                    "                \"photo_reference\": \"AdCG2DMpbfD9sADJPlZS3p2rHGyqtV7cGwrQFVgE6q7GF2mejZuVZ1YnD71g3FNrw3y2q45Kd5-U28ScXvsdx6kWL61OoKkCl5EHhoHKJMv1i2tK1gzS5w1SXvdPSxJW0rjmK_K-acrVdVd1M_3qAFybtP1Yb5mZWcIdx-M19UtxDmjAQkZ3\",\n" +
                    "                \"width\": 1872\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"place_id\": \"ChIJ8XxIow551oYR8Mv5yRUUP5A\",\n" +
                    "        \"plus_code\": {\n" +
                    "            \"compound_code\": \"4X9C+VF Drexel Heights, AZ, USA\",\n" +
                    "            \"global_code\": \"854C4X9C+VF\"\n" +
                    "        },\n" +
                    "        \"rating\": 5,\n" +
                    "        \"reference\": \"ChIJ8XxIow551oYR8Mv5yRUUP5A\",\n" +
                    "        \"scope\": \"GOOGLE\",\n" +
                    "        \"types\": [\n" +
                    "            \"transit_station\",\n" +
                    "            \"point_of_interest\",\n" +
                    "            \"establishment\"\n" +
                    "        ],\n" +
                    "        \"user_ratings_total\": 1,\n" +
                    "        \"vicinity\": \"United States\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"business_status\": \"OPERATIONAL\",\n" +
                    "        \"geometry\": {\n" +
                    "            \"location\": {\n" +
                    "                \"lat\": 32.124141,\n" +
                    "                \"lng\": -111.029046\n" +
                    "            },\n" +
                    "            \"viewport\": {\n" +
                    "                \"northeast\": {\n" +
                    "                    \"lat\": 32.1254899802915,\n" +
                    "                    \"lng\": -111.0276970197085\n" +
                    "                },\n" +
                    "                \"southwest\": {\n" +
                    "                    \"lat\": 32.1227920197085,\n" +
                    "                    \"lng\": -111.0303949802915\n" +
                    "                }\n" +
                    "            }\n" +
                    "        },\n" +
                    "        \"icon\": \"https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png\",\n" +
                    "        \"icon_background_color\": \"#7B9EB0\",\n" +
                    "        \"icon_mask_base_uri\": \"https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet\",\n" +
                    "        \"name\": \"Cardinal/Christopher Dr (SW)\",\n" +
                    "        \"place_id\": \"ChIJDTqOmgl51oYRYRbUfpmMDmM\",\n" +
                    "        \"plus_code\": {\n" +
                    "            \"compound_code\": \"4XFC+M9 Drexel Heights, AZ, USA\",\n" +
                    "            \"global_code\": \"854C4XFC+M9\"\n" +
                    "        },\n" +
                    "        \"reference\": \"ChIJDTqOmgl51oYRYRbUfpmMDmM\",\n" +
                    "        \"scope\": \"GOOGLE\",\n" +
                    "        \"types\": [\n" +
                    "            \"transit_station\",\n" +
                    "            \"point_of_interest\",\n" +
                    "            \"establishment\"\n" +
                    "        ],\n" +
                    "        \"vicinity\": \"United States\"\n" +
                    "   }\n"+
                    "}"
            val JSON_response = parseArray("JSON_string")
            for (i in 0 until (JSON_response?.length() ?: 0)) {
                val business_object = JSON_response?.getJSONObject(i)
                val geometry_object = business_object?.getJSONObject("geometry")
                val location_object = geometry_object?.getJSONObject("location")

                val latitude = location_object?.getDouble("lat") ?: 0.0

                val longitude = location_object?.getDouble("lng") ?: 0.0

                val transitShop = TransitShop(latitude, longitude)
                allTransitShops.add(transitShop)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }





    fun get_google_maps_bike_rentals() {
        try {
            val JSON_string = "[\n" +
                    "    {\n" +
                    "        \"business_status\": \"OPERATIONAL\",\n" +
                    "        \"geometry\": {\n" +
                    "            \"location\": {\n" +
                    "                \"lat\": 32.157255,\n" +
                    "                \"lng\": -111.022411\n" +
                    "            },\n" +
                    "            \"viewport\": {\n" +
                    "                \"northeast\": {\n" +
                    "                    \"lat\": 32.15860657989273,\n" +
                    "                    \"lng\": -111.0211520201073\n" +
                    "                },\n" +
                    "                \"southwest\": {\n" +
                    "                    \"lat\": 32.15590692010728,\n" +
                    "                    \"lng\": -111.0238516798927\n" +
                    "                }\n" +
                    "            }\n" +
                    "        },\n" +
                    "        \"icon\": \"https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/park-71.png\",\n" +
                    "        \"icon_background_color\": \"#4DB546\",\n" +
                    "        \"icon_mask_base_uri\": \"https://maps.gstatic.com/mapfiles/place_api/icons/v2/tree_pinlet\",\n" +
                    "        \"name\": \"Winston Reynolds - Manzanita Park\",\n" +
                    "        \"photos\": [\n" +
                    "            {\n" +
                    "                \"height\": 4096,\n" +
                    "                \"html_attributions\": [\n" +
                    "                    \"<a href=\\\"https://maps.google.com/maps/contrib/109610250907554081982\\\">David Peaslee</a>\"\n" +
                    "                ],\n" +
                    "                \"photo_reference\": \"AdCG2DMnyMCqRQPlyG3D8asJMmnLuIpnIoA-e3ic5z8T1M8s0YnabLE7W-ajOglV5HxQMWFWVstyG7OuAbUjKrCao_lhwoFJ5PPnQdxtQ4PLP-Gzz9E62isCbOjAJxM8rsXM8gBGWByzzpmnMKU3pJBmsSgSTQpiYpH1iQChfzBZf3K9OSWR\",\n" +
                    "                \"width\": 3072\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"place_id\": \"ChIJvS44gMJ51oYRcMOHTwKmYmg\",\n" +
                    "        \"plus_code\": {\n" +
                    "            \"compound_code\": \"5X4H+W2 Tucson, Arizona\",\n" +
                    "            \"global_code\": \"854C5X4H+W2\"\n" +
                    "        },\n" +
                    "        \"rating\": 4.6,\n" +
                    "        \"reference\": \"ChIJvS44gMJ51oYRcMOHTwKmYmg\",\n" +
                    "        \"scope\": \"GOOGLE\",\n" +
                    "        \"types\": [\n" +
                    "            \"park\",\n" +
                    "            \"tourist_attraction\",\n" +
                    "            \"point_of_interest\",\n" +
                    "            \"establishment\"\n" +
                    "        ],\n" +
                    "        \"user_ratings_total\": 659,\n" +
                    "        \"vicinity\": \"5200 S Westover Ave, Tucson\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"business_status\": \"OPERATIONAL\",\n" +
                    "        \"geometry\": {\n" +
                    "            \"location\": {\n" +
                    "                \"lat\": 32.157255,\n" +
                    "                \"lng\": -111.022411\n" +
                    "            },\n" +
                    "            \"viewport\": {\n" +
                    "                \"northeast\": {\n" +
                    "                    \"lat\": 32.15860657989273,\n" +
                    "                    \"lng\": -111.0211520201073\n" +
                    "                },\n" +
                    "                \"southwest\": {\n" +
                    "                    \"lat\": 32.15590692010728,\n" +
                    "                    \"lng\": -111.0238516798927\n" +
                    "                }\n" +
                    "            }\n" +
                    "        },\n" +
                    "        \"icon\": \"https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/park-71.png\",\n" +
                    "        \"icon_background_color\": \"#4DB546\",\n" +
                    "        \"icon_mask_base_uri\": \"https://maps.gstatic.com/mapfiles/place_api/icons/v2/tree_pinlet\",\n" +
                    "        \"name\": \"Winston Reynolds - Manzanita Park\",\n" +
                    "        \"photos\": [\n" +
                    "            {\n" +
                    "                \"height\": 4096,\n" +
                    "                \"html_attributions\": [\n" +
                    "                    \"<a href=\\\"https://maps.google.com/maps/contrib/109610250907554081982\\\">David Peaslee</a>\"\n" +
                    "                ],\n" +
                    "                \"photo_reference\": \"AdCG2DO1hU4Usj9Y3QisRAYlBbqH0lyAWTIm4ZJcPuETKFI1w3vnAam7cw9oMnDi1NZrLduy0dlnNhl_bBU3q2m5v33jjKx0quzRB9bXM5pGFpJiMkylCljG4K5PyzC-nMrfOQqx3PM_hHLLjE_8Xt2GokG9Hx0j_V_-vKJV---Si1EQqhQ9\",\n" +
                    "                \"width\": 3072\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"place_id\": \"ChIJvS44gMJ51oYRcMOHTwKmYmg\",\n" +
                    "        \"plus_code\": {\n" +
                    "            \"compound_code\": \"5X4H+W2 Tucson, Arizona\",\n" +
                    "            \"global_code\": \"854C5X4H+W2\"\n" +
                    "        },\n" +
                    "        \"rating\": 4.6,\n" +
                    "        \"reference\": \"ChIJvS44gMJ51oYRcMOHTwKmYmg\",\n" +
                    "        \"scope\": \"GOOGLE\",\n" +
                    "        \"types\": [\n" +
                    "            \"park\",\n" +
                    "            \"tourist_attraction\",\n" +
                    "            \"point_of_interest\",\n" +
                    "            \"establishment\"\n" +
                    "        ],\n" +
                    "        \"user_ratings_total\": 659,\n" +
                    "        \"vicinity\": \"5200 S Westover Ave, Tucson\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"business_status\": \"OPERATIONAL\",\n" +
                    "        \"geometry\": {\n" +
                    "            \"location\": {\n" +
                    "                \"lat\": 32.1784054,\n" +
                    "                \"lng\": -110.9514553\n" +
                    "            },\n" +
                    "            \"viewport\": {\n" +
                    "                \"northeast\": {\n" +
                    "                    \"lat\": 32.17964822989272,\n" +
                    "                    \"lng\": -110.9499714201073\n" +
                    "                },\n" +
                    "                \"southwest\": {\n" +
                    "                    \"lat\": 32.17694857010728,\n" +
                    "                    \"lng\": -110.9526710798927\n" +
                    "                }\n" +
                    "            }\n" +
                    "        },\n" +
                    "        \"icon\": \"https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/shopping-71.png\",\n" +
                    "        \"icon_background_color\": \"#4B96F3\",\n" +
                    "        \"icon_mask_base_uri\": \"https://maps.gstatic.com/mapfiles/place_api/icons/v2/shopping_pinlet\",\n" +
                    "        \"name\": \"Ajo Bikes\",\n" +
                    "        \"opening_hours\": {\n" +
                    "            \"open_now\": true\n" +
                    "        },\n" +
                    "        \"photos\": [\n" +
                    "            {\n" +
                    "                \"height\": 3072,\n" +
                    "                \"html_attributions\": [\n" +
                    "                    \"<a href=\\\"https://maps.google.com/maps/contrib/116565735029084260597\\\">Kris Bjelk</a>\"\n" +
                    "                ],\n" +
                    "                \"photo_reference\": \"AdCG2DNrRuGkzHmRnFwjZagKkGlVn8vIhvBlRAwCewAt-gVNSSDob8WFbGZCjKo4ceh_BBpEInPcty9xNFHDh6XZzajcB01IzIlplgUh8GjUJD8hfGTTAkMC65FirZQ-TxwQRb6Rj2521DKwIs1mKQt15E9QiLwPbBUM4xk6N68dd4cY34dA\",\n" +
                    "                \"width\": 4080\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"place_id\": \"ChIJ3XkIc3561oYREWGVi5l2vHQ\",\n" +
                    "        \"plus_code\": {\n" +
                    "            \"compound_code\": \"52HX+9C Tucson, Arizona\",\n" +
                    "            \"global_code\": \"854F52HX+9C\"\n" +
                    "        },\n" +
                    "        \"rating\": 4.8,\n" +
                    "        \"reference\": \"ChIJ3XkIc3561oYREWGVi5l2vHQ\",\n" +
                    "        \"scope\": \"GOOGLE\",\n" +
                    "        \"types\": [\n" +
                    "            \"bicycle_store\",\n" +
                    "            \"store\",\n" +
                    "            \"point_of_interest\",\n" +
                    "            \"establishment\"\n" +
                    "        ],\n" +
                    "        \"user_ratings_total\": 622,\n" +
                    "        \"vicinity\": \"1301 E Ajo Way #117, Tucson\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"business_status\": \"OPERATIONAL\",\n" +
                    "        \"geometry\": {\n" +
                    "            \"location\": {\n" +
                    "                \"lat\": 32.1784054,\n" +
                    "                \"lng\": -110.9514553\n" +
                    "            },\n" +
                    "            \"viewport\": {\n" +
                    "                \"northeast\": {\n" +
                    "                    \"lat\": 32.17964822989272,\n" +
                    "                    \"lng\": -110.9499714201073\n" +
                    "                },\n" +
                    "                \"southwest\": {\n" +
                    "                    \"lat\": 32.17694857010728,\n" +
                    "                    \"lng\": -110.9526710798927\n" +
                    "                }\n" +
                    "            }\n" +
                    "        },\n" +
                    "        \"icon\": \"https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/shopping-71.png\",\n" +
                    "        \"icon_background_color\": \"#4B96F3\",\n" +
                    "        \"icon_mask_base_uri\": \"https://maps.gstatic.com/mapfiles/place_api/icons/v2/shopping_pinlet\",\n" +
                    "        \"name\": \"Ajo Bikes\",\n" +
                    "        \"opening_hours\": {\n" +
                    "            \"open_now\": true\n" +
                    "        },\n" +
                    "        \"photos\": [\n" +
                    "            {\n" +
                    "                \"height\": 3072,\n" +
                    "                \"html_attributions\": [\n" +
                    "                    \"<a href=\\\"https://maps.google.com/maps/contrib/116565735029084260597\\\">Kris Bjelk</a>\"\n" +
                    "                ],\n" +
                    "                \"photo_reference\": \"AdCG2DNnQ6UZyNAGHLxJ9vT5YUmRw7NdSTYkDOc5c7UbexHYYVzeeVy4zt5C-OHt6VAAeDbCtCNtvPj_dxjxSM0xjooDzSA0zsfcfUnK1zsxMI_VQS08_m2L25OESgf2w1tCCgf3GB3gZalCF_igPjCfiivjW-6ZJ8J7-2rHm4aKSWLjpMYk\",\n" +
                    "                \"width\": 4080\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"place_id\": \"ChIJ3XkIc3561oYREWGVi5l2vHQ\",\n" +
                    "        \"plus_code\": {\n" +
                    "            \"compound_code\": \"52HX+9C Tucson, Arizona\",\n" +
                    "            \"global_code\": \"854F52HX+9C\"\n" +
                    "        },\n" +
                    "        \"rating\": 4.8,\n" +
                    "        \"reference\": \"ChIJ3XkIc3561oYREWGVi5l2vHQ\",\n" +
                    "        \"scope\": \"GOOGLE\",\n" +
                    "        \"types\": [\n" +
                    "            \"bicycle_store\",\n" +
                    "            \"store\",\n" +
                    "            \"point_of_interest\",\n" +
                    "            \"establishment\"\n" +
                    "        ],\n" +
                    "        \"user_ratings_total\": 622,\n" +
                    "        \"vicinity\": \"1301 E Ajo Way #117, Tucson\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "        \"business_status\": \"OPERATIONAL\",\n" +
                    "        \"geometry\": {\n" +
                    "            \"location\": {\n" +
                    "                \"lat\": 32.1777929,\n" +
                    "                \"lng\": -110.8406468\n" +
                    "            },\n" +
                    "            \"viewport\": {\n" +
                    "                \"northeast\": {\n" +
                    "                    \"lat\": 32.17913477989271,\n" +
                    "                    \"lng\": -110.8393230201073\n" +
                    "                },\n" +
                    "                \"southwest\": {\n" +
                    "                    \"lat\": 32.17643512010727,\n" +
                    "                    \"lng\": -110.8420226798927\n" +
                    "                }\n" +
                    "            }\n" +
                    "        },\n" +
                    "        \"icon\": \"https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png\",\n" +
                    "        \"icon_background_color\": \"#7B9EB0\",\n" +
                    "        \"icon_mask_base_uri\": \"https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet\",\n" +
                    "        \"name\": \"Tucson Adventure Rentals\",\n" +
                    "        \"opening_hours\": {\n" +
                    "            \"open_now\": true\n" +
                    "        },\n" +
                    "        \"photos\": [\n" +
                    "            {\n" +
                    "                \"height\": 2852,\n" +
                    "                \"html_attributions\": [\n" +
                    "                    \"<a href=\\\"https://maps.google.com/maps/contrib/108099683749806414873\\\">A Google User</a>\"\n" +
                    "                ],\n" +
                    "                \"photo_reference\": \"AdCG2DPKZVMc0A4vGLxpR_GUEUdmuwATixWDK9XtKQQM2XpCBOBSXs7-qjiul6aCcPz-wKrIVWEvvMPUtZregmSC3RoHUGqAMo9osUYjUHjZSCmw_Kb4EVJybc4oSZv8Gr_ezutZT-GWzpQ5D-vRwlP1azxcXvyd_PP3srHEFxLlKQduSevX\",\n" +
                    "                \"width\": 4306\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"place_id\": \"ChIJDbEGLJZh1oYRQd2fGwgQ_fI\",\n" +
                    "        \"plus_code\": {\n" +
                    "            \"compound_code\": \"55H5+4P Tucson, Arizona\",\n" +
                    "            \"global_code\": \"854F55H5+4P\"\n" +
                    "        },\n" +
                    "        \"rating\": 4.7,\n" +
                    "        \"reference\": \"ChIJDbEGLJZh1oYRQd2fGwgQ_fI\",\n" +
                    "        \"scope\": \"GOOGLE\",\n" +
                    "        \"types\": [\n" +
                    "            \"travel_agency\",\n" +
                    "            \"point_of_interest\",\n" +
                    "            \"establishment\"\n" +
                    "        ],\n" +
                    "        \"user_ratings_total\": 79,\n" +
                    "        \"vicinity\": \"3797 S Kolb Rd, Tucson\"\n" +
                    "    }\n"+
                    "]"

            val JSON_response = parseArray("JSON_string")
            for (i in 0 until (JSON_response?.length() ?: 0)) {
                val business_object = JSON_response?.getJSONObject(i)
                val geometry_object = business_object?.getJSONObject("geometry")
                val location_object = geometry_object?.getJSONObject("location")
                val latitude = location_object?.getDouble("lat") ?:0.0
                val longitude = location_object?.getDouble("lng") ?: 0.0

                val business_name = business_object?.getString("name") ?: "Unknown"

                val business_rating = business_object?.getDouble("rating")

                val business_vicinity = business_object?.getString("vicinity")


                val bikeShop = BikeShop(latitude, longitude, business_name)
                allBikeShops.add(bikeShop)
            }
        } catch (e: JSONException) {
            e.printStackTrace()}
        }
    }



    fun parseObject(json: String): JSONObject? {
        var jsonObject: JSONObject? = null
        try {
            jsonObject = JSONObject(json)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return jsonObject
    }

    fun parseArray(json: String): JSONArray? {
        var jsonArray: JSONArray? = null
        try {
            jsonArray = JSONArray(json)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return jsonArray
    }
//
//    fun goToCalendar(view: View) {
//        val intent = Intent(this, CalenderActivity::class.java)
//        startActivity(intent)
//    }
//
//    fun goToCityPage(view: View) {
//        val intent = Intent(this, CityActivity::class.java)
//        startActivity(intent)
//    }

class BikeShop(
    val lat: Double,
    val lng: Double,
    val name: String
)
class TransitShop(
    val lat: Double,
    val lng: Double
)