package edu.arizona.ISTA498.trektraveler

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.BaseAdapter
import com.google.android.material.card.MaterialCardView

class BikeAdapter(private val context: Context, private val bikeShops: List<BikeShop>) : BaseAdapter() {

    override fun getCount(): Int {
        return bikeShops.size
    }

    override fun getItem(position: Int): Any {
        return bikeShops[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.card_bike, parent, false)

        val bikeShop = getItem(position) as BikeShop

        val cardTitle = view.findViewById<TextView>(R.id.cardTitle)
        val cardDescription = view.findViewById<TextView>(R.id.cardDescription)
        val directionsButton = view.findViewById<Button>(R.id.directionsButton)

        cardTitle.text = bikeShop.name
        cardDescription.text = "Located at: ${bikeShop.vicinity}"

        directionsButton.setOnClickListener {
            val uri = Uri.parse("geo:${bikeShop.latitude},${bikeShop.longitude}?q=${Uri.encode(bikeShop.name)}")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.google.android.apps.maps")
            context.startActivity(intent)
        }

        return view
    }
}