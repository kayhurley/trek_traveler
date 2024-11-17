package edu.arizona.ISTA498.trektraveler

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.io.BufferedReader
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.*

class InfoTab : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.info_screen, container, false)

        val highLowTextView: TextView = view.findViewById(R.id.highLowText)
        val typicalWeatherText: TextView = view.findViewById(R.id.typicalWeatherText)
        val weatherIcon: ImageView = view.findViewById(R.id.weatherIcon) // Replace with your actual ID
        val weatherDescription: TextView = view.findViewById(R.id.weatherDescription) // Replace with actual ID

        // Use the selected date from CalendarActivity
        val selectedDate = CalenderActivity.selectedDate ?: "No Date Selected"
        highLowTextView.text = "Loading weather data for $selectedDate..."

        // Update "Typical Weather for [Month] [Day]"
        updateTypicalWeatherText(selectedDate, typicalWeatherText)

        // Calculate and display high and low temperatures
        calculateHighLow(selectedDate, highLowTextView)

        // Check precipitation and update icon/text
        checkPrecipitation(selectedDate, weatherIcon, weatherDescription)

        return view
    }

    private fun updateTypicalWeatherText(userSelectedDate: String, typicalWeatherText: TextView) {
        try {
            // Parse the selected date to extract Month and Day
            val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()) // Your current date format
            val date = dateFormat.parse(userSelectedDate)
            val formattedDate = SimpleDateFormat("MMMM d", Locale.getDefault()).format(date!!)

            typicalWeatherText.text = "Typical Weather for $formattedDate"
        } catch (e: Exception) {
            typicalWeatherText.text = "Error displaying date."
            Log.e("InfoTab", "Error formatting date: ${e.message}")
        }
    }

    private fun calculateHighLow(userSelectedDate: String, resultView: TextView) {
        try {
            val weatherData = loadCSVData()

            // Parse the selected date to extract MM-DD
            val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()) // Match format used in CalendarActivity
            val date = dateFormat.parse(userSelectedDate)
            val monthDayFormat = SimpleDateFormat("MM-dd", Locale.getDefault())
            val selectedMonthDay = monthDayFormat.format(date!!)

            Log.d("WeatherData", "Looking for data matching: $selectedMonthDay")

            // Filter historical data for the same MM-DD across all available years
            val filteredData = weatherData.filter { it.date.substring(5) == selectedMonthDay }

            if (filteredData.isNotEmpty()) {
                val highTemp = filteredData.maxOf { it.maxTemp }
                val lowTemp = filteredData.minOf { it.minTemp }
                resultView.text = "High: ${highTemp}°F, Low: ${lowTemp}°F"
            } else {
                resultView.text = "No data available for $userSelectedDate."
                Log.d("WeatherData", "No matching data found for: $selectedMonthDay")
            }
        } catch (e: Exception) {
            resultView.text = "Error loading data: ${e.message}"
            Log.e("WeatherData", "Error: ${e.message}")
        }
    }

    private fun checkPrecipitation(userSelectedDate: String, weatherIcon: ImageView, weatherDescription: TextView) {
        try {
            val precipitationData = loadPrecipitationData()

            // Parse the selected date to match MM-DD format
            val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
            val date = dateFormat.parse(userSelectedDate)
            val monthDayFormat = SimpleDateFormat("M-d", Locale.getDefault()) // Match dataset format
            val selectedMonthDay = monthDayFormat.format(date!!)

            Log.d("PrecipitationData", "Checking precipitation for: $selectedMonthDay")

            // Retrieve precipitation status for the selected date
            val isRainy = precipitationData[selectedMonthDay] ?: false

            if (isRainy) {
                weatherIcon.setImageResource(R.drawable.cloud) // Use your storm icon resource
                weatherDescription.text = "Chances of rain"
            } else {
                weatherIcon.setImageResource(R.drawable.sun) // Use your sun icon resource
                weatherDescription.text = "We predict a sunny day!"
            }
        } catch (e: Exception) {
            weatherDescription.text = "Error checking weather."
            Log.e("PrecipitationData", "Error: ${e.message}")
        }
    }

    private fun loadCSVData(): List<WeatherData> {
        val dataList = mutableListOf<WeatherData>()
        try {
            val inputStream = requireContext().assets.open("Open Meteo Historical Data.csv")
            val reader = BufferedReader(InputStreamReader(inputStream))
            reader.use {
                it.readLine() // Skip header
                var line: String?
                while (it.readLine().also { line = it } != null) {
                    val tokens = line!!.split(",")
                    val date = tokens[0].trim() // First column: date in YYYY-MM-DD
                    val maxTemp = tokens[1].trim().toDouble() // Second column: max temp
                    val minTemp = tokens[2].trim().toDouble() // Third column: min temp
                    dataList.add(WeatherData(date, maxTemp, minTemp))
                }
            }
        } catch (e: Exception) {
            throw RuntimeException("Error loading CSV: ${e.message}")
        }
        return dataList
    }

    private fun loadPrecipitationData(): Map<String, Boolean> {
        val dataMap = mutableMapOf<String, Boolean>()
        try {
            val inputStream = requireContext().assets.open("Precipitation Data from Kailey Hurley.csv")
            val reader = BufferedReader(InputStreamReader(inputStream))
            reader.use {
                it.readLine() // Skip header
                var line: String?
                while (it.readLine().also { line = it } != null) {
                    val tokens = line!!.split(",")
                    val date = tokens[0].trim() // First column: date in M-D
                    val isRainy = tokens[1].trim().toBoolean() // Second column: boolean
                    dataMap[date] = isRainy
                }
            }
        } catch (e: Exception) {
            throw RuntimeException("Error loading Precipitation CSV: ${e.message}")
        }
        return dataMap
    }

    private data class WeatherData(val date: String, val maxTemp: Double, val minTemp: Double)
}