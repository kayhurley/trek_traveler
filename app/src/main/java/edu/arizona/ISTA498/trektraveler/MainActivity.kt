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
   fun get_weather_score_data() {
       GlobalScope.launch(Dispatchers.I0) {
           try {
               val response =
                   URL("https://api.openweathermap.org/data/2.5/weather?lat=32.2226&lon=-110.9747&appid=772a8fce0fd1f9e51c34f53b07845329").readText()
               val jsonresponse = parseObject(response)
               val weather = jsonresponse?.getJSONArray("weather")
                if (weather.length() > 0) {
                    val weather_object = weather[0]
                    val weather_main = weather_object.getString("main")
                    val weather_description = weather_object.getString("description")
                }


           } catch (e: java.lang.Exception) {
               print(e.message)
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
    try{
        jsonArray = JSONArray(json)
    }catch (e: JSONException){
        e.printStackTrace()
    }

    return jsonArray
}

    fun goToCalendar(view: View){
        val intent = Intent(this, CalenderActivity::class.java)
        startActivity(intent)
    }

    fun goToCityPage(view: View){
        val intent = Intent(this, CityActivity::class.java)
        startActivity(intent)
    }
}