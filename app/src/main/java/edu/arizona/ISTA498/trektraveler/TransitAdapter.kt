import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView
import edu.arizona.ISTA498.trektraveler.R
import edu.arizona.ISTA498.trektraveler.TransitShop

class TransitAdapter(private val context: Context, private val transitStops: List<TransitShop>) : BaseAdapter() {

    override fun getCount(): Int = transitStops.size

    override fun getItem(position: Int): Any = transitStops[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.card_transit, parent, false)

        val stop = transitStops[position]

        // Using card IDs from previous material card file.
        view.findViewById<TextView>(R.id.cardTitle).text = stop.name
        view.findViewById<TextView>(R.id.cardDescription).text = "Bus stop"
        view.findViewById<MaterialCardView>(R.id.cardView).setCardBackgroundColor(ContextCompat.getColor(context, R.color.bkgSection))

        // Setup Directions Button
        view.findViewById<Button>(R.id.directionsButton).setOnClickListener {
            val uri = "geo:${stop.latitude},${stop.longitude}?q=${stop.name}"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            context.startActivity(intent)
        }

        return view
    }
}