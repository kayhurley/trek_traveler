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
        "photos": [
            {
                "height": 4160,
                "html_attributions": [
                    "<a href=\"https://maps.google.com/maps/contrib/109781904960670385593\">Luis M Gaxiola S</a>"
                ],
                "photo_reference": "AdCG2DMpbfD9sADJPlZS3p2rHGyqtV7cGwrQFVgE6q7GF2mejZuVZ1YnD71g3FNrw3y2q45Kd5-U28ScXvsdx6kWL61OoKkCl5EHhoHKJMv1i2tK1gzS5w1SXvdPSxJW0rjmK_K-acrVdVd1M_3qAFybtP1Yb5mZWcIdx-M19UtxDmjAQkZ3",
                "width": 1872
            }
        ],
        "place_id": "ChIJ8XxIow551oYR8Mv5yRUUP5A",
        "plus_code": {
            "compound_code": "4X9C+VF Drexel Heights, AZ, USA",
            "global_code": "854C4X9C+VF"
        },
        "rating": 5,
        "reference": "ChIJ8XxIow551oYR8Mv5yRUUP5A",
        "scope": "GOOGLE",
        "types": [
            "transit_station",
            "point_of_interest",
            "establishment"
        ],
        "user_ratings_total": 1,
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
    },
    {
        "business_status": "OPERATIONAL",
        "geometry": {
            "location": {
                "lat": 32.119255,
                "lng": -111.029395
            },
            "viewport": {
                "northeast": {
                    "lat": 32.1205720302915,
                    "lng": -111.0280454697085
                },
                "southwest": {
                    "lat": 32.1178740697085,
                    "lng": -111.0307434302915
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
        "icon_background_color": "#7B9EB0",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
        "name": "Los Reales/Cardinal (NW)",
        "place_id": "ChIJyduwlQ551oYRn6M6M-6zBV4",
        "plus_code": {
            "compound_code": "4X9C+P6 Drexel Heights, AZ, USA",
            "global_code": "854C4X9C+P6"
        },
        "reference": "ChIJyduwlQ551oYRn6M6M-6zBV4",
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
                "lat": 32.124484,
                "lng": -111.028958
            },
            "viewport": {
                "northeast": {
                    "lat": 32.1258332302915,
                    "lng": -111.0276194197085
                },
                "southwest": {
                    "lat": 32.1231352697085,
                    "lng": -111.0303173802915
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
        "icon_background_color": "#7B9EB0",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
        "name": "Cardinal/Tigre (NE)",
        "place_id": "ChIJH9vokQl51oYRHcHgkD36Pbg",
        "plus_code": {
            "compound_code": "4XFC+QC Drexel Heights, AZ, USA",
            "global_code": "854C4XFC+QC"
        },
        "reference": "ChIJH9vokQl51oYRHcHgkD36Pbg",
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
                "lat": 32.119078,
                "lng": -111.033223
            },
            "viewport": {
                "northeast": {
                    "lat": 32.1204812302915,
                    "lng": -111.0318741197085
                },
                "southwest": {
                    "lat": 32.1177832697085,
                    "lng": -111.0345720802915
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
        "icon_background_color": "#7B9EB0",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
        "name": "Los Reales/Cmo Alegre (SE)",
        "photos": [
            {
                "height": 3024,
                "html_attributions": [
                    "<a href=\"https://maps.google.com/maps/contrib/105693031635305728700\">Alex</a>"
                ],
                "photo_reference": "AdCG2DPFY9RreLWvlMZrzJ3VtxD5GQlXjIQ_CBqLU0c6cIEHw2hxDC1_TdOhAEMXTWDy9611DOgQd3l7ekSjisipZJOK8ik-cOo2OFN8RDyjGs_rTdGIAG6d4Xsl3YlZRPf6d3S9ueCoJz4MClu10P7N_fiiJFY9mQi4_UZvtrpapcFc7dHW",
                "width": 4032
            }
        ],
        "place_id": "ChIJ5QGDRA951oYRgpOtt1MYJYI",
        "plus_code": {
            "compound_code": "4X98+JP Drexel Heights, AZ, USA",
            "global_code": "854C4X98+JP"
        },
        "reference": "ChIJ5QGDRA951oYRgpOtt1MYJYI",
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
                "lat": 32.127696,
                "lng": -111.02903
            },
            "viewport": {
                "northeast": {
                    "lat": 32.1290448802915,
                    "lng": -111.0276344697085
                },
                "southwest": {
                    "lat": 32.1263469197085,
                    "lng": -111.0303324302915
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
        "icon_background_color": "#7B9EB0",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
        "name": "Cardinal/Pajarito (SW)",
        "place_id": "ChIJ4X1EOKd51oYRi6-YD0HaHnE",
        "plus_code": {
            "compound_code": "4XHC+39 Drexel Heights, AZ, USA",
            "global_code": "854C4XHC+39"
        },
        "reference": "ChIJ4X1EOKd51oYRi6-YD0HaHnE",
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
                "lat": 32.127804,
                "lng": -111.028904
            },
            "viewport": {
                "northeast": {
                    "lat": 32.1291530302915,
                    "lng": -111.0275504197085
                },
                "southwest": {
                    "lat": 32.1264550697085,
                    "lng": -111.0302483802915
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
        "icon_background_color": "#7B9EB0",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
        "name": "Cardinal/Pajarito (SE)",
        "place_id": "ChIJSRvWOad51oYRPY2LfIKapOo",
        "plus_code": {
            "compound_code": "4XHC+4C Drexel Heights, AZ, USA",
            "global_code": "854C4XHC+4C"
        },
        "rating": 3,
        "reference": "ChIJSRvWOad51oYRPY2LfIKapOo",
        "scope": "GOOGLE",
        "types": [
            "transit_station",
            "point_of_interest",
            "establishment"
        ],
        "user_ratings_total": 1,
        "vicinity": "United States"
    },
    {
        "business_status": "OPERATIONAL",
        "geometry": {
            "location": {
                "lat": 32.119235,
                "lng": -111.033849
            },
            "viewport": {
                "northeast": {
                    "lat": 32.1205571802915,
                    "lng": -111.0324992197085
                },
                "southwest": {
                    "lat": 32.1178592197085,
                    "lng": -111.0351971802915
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
        "icon_background_color": "#7B9EB0",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
        "name": "Los Reales/Cmo Alegre (NW)",
        "place_id": "ChIJfynCWQ951oYRElYeG3dxwl0",
        "plus_code": {
            "compound_code": "4X98+MF Drexel Heights, AZ, USA",
            "global_code": "854C4X98+MF"
        },
        "reference": "ChIJfynCWQ951oYRElYeG3dxwl0",
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
                "lat": 32.131237,
                "lng": -111.0288
            },
            "viewport": {
                "northeast": {
                    "lat": 32.1325884302915,
                    "lng": -111.0274863697085
                },
                "southwest": {
                    "lat": 32.1298904697085,
                    "lng": -111.0301843302915
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
        "icon_background_color": "#7B9EB0",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
        "name": "Cardinal/Pincushion (NE)",
        "place_id": "ChIJSfASr6Z51oYRBrsYaDc5B0g",
        "plus_code": {
            "compound_code": "4XJC+FF Drexel Heights, AZ, USA",
            "global_code": "854C4XJC+FF"
        },
        "reference": "ChIJSfASr6Z51oYRBrsYaDc5B0g",
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
                "lat": 32.119179,
                "lng": -111.037994
            },
            "viewport": {
                "northeast": {
                    "lat": 32.1205223802915,
                    "lng": -111.0366449697085
                },
                "southwest": {
                    "lat": 32.1178244197085,
                    "lng": -111.0393429302915
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
        "icon_background_color": "#7B9EB0",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
        "name": "Los Reales/Cmo de la Tierra (NW)",
        "photos": [
            {
                "height": 2887,
                "html_attributions": [
                    "<a href=\"https://maps.google.com/maps/contrib/112129263127999633260\">Manny Casillas</a>"
                ],
                "photo_reference": "AdCG2DPD5vBR7uX_Li-AGjGfzz2izaqznM5xzTZX3Sf5PERkwcLP64t34Sr2Tq65XV27VDi42F7quPorVCGCIgCtgTo98_vp1Y-5I8CKoLjmscOghAfxsA5eboHBaj9YyYHyKSTSufZupaQCoypwh0I_N5qDYG9Y0p6hCZhTjYIUF0RYk_Ql",
                "width": 3853
            }
        ],
        "place_id": "ChIJeV6YJwR51oYR7QLiBDSRDl8",
        "plus_code": {
            "compound_code": "4X96+MR Drexel Heights, AZ, USA",
            "global_code": "854C4X96+MR"
        },
        "reference": "ChIJeV6YJwR51oYR7QLiBDSRDl8",
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
                "lat": 32.13358150000001,
                "lng": -111.029133
            },
            "viewport": {
                "northeast": {
                    "lat": 32.13499923029151,
                    "lng": -111.0277842197085
                },
                "southwest": {
                    "lat": 32.13230126970851,
                    "lng": -111.0304821802915
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
        "icon_background_color": "#7B9EB0",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
        "name": "Cardinal/Valencia",
        "place_id": "ChIJg10Nb6R51oYRQ45nREq9KXw",
        "plus_code": {
            "compound_code": "4XMC+C8 Drexel Heights, AZ, USA",
            "global_code": "854C4XMC+C8"
        },
        "reference": "ChIJg10Nb6R51oYRQ45nREq9KXw",
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
                "lat": 32.1323109,
                "lng": -111.0289961
            },
            "viewport": {
                "northeast": {
                    "lat": 32.1336598302915,
                    "lng": -111.0276005197085
                },
                "southwest": {
                    "lat": 32.1309618697085,
                    "lng": -111.0302984802915
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
        "icon_background_color": "#7B9EB0",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
        "name": "Cardinal/Valencia (SW)",
        "place_id": "ChIJ1VOqGqR51oYRaKkyOJ1mOsw",
        "plus_code": {
            "compound_code": "4XJC+WC Drexel Heights, AZ, USA",
            "global_code": "854C4XJC+WC"
        },
        "reference": "ChIJ1VOqGqR51oYRaKkyOJ1mOsw",
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
                "lat": 32.119045,
                "lng": -111.039581
            },
            "viewport": {
                "northeast": {
                    "lat": 32.1204344802915,
                    "lng": -111.0382323697085
                },
                "southwest": {
                    "lat": 32.1177365197085,
                    "lng": -111.0409303302915
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
        "icon_background_color": "#7B9EB0",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
        "name": "Los Reales/C de la Bajada (SW)",
        "place_id": "ChIJ2S7EqQZ51oYRIBaWe_L3-n0",
        "plus_code": {
            "compound_code": "4X96+J5 Drexel Heights, AZ, USA",
            "global_code": "854C4X96+J5"
        },
        "reference": "ChIJ2S7EqQZ51oYRIBaWe_L3-n0",
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
                "lat": 32.1335913,
                "lng": -111.0261198
            },
            "viewport": {
                "northeast": {
                    "lat": 32.1350043802915,
                    "lng": -111.0247710197085
                },
                "southwest": {
                    "lat": 32.1323064197085,
                    "lng": -111.0274689802915
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/bus-71.png",
        "icon_background_color": "#10BDFF",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/bus_share_taxi_pinlet",
        "name": "Valencia/Cardinal (SE)",
        "place_id": "ChIJXWixt6N51oYRtollNe9e2lA",
        "plus_code": {
            "compound_code": "4XMF+CH Tucson, AZ, USA",
            "global_code": "854C4XMF+CH"
        },
        "reference": "ChIJXWixt6N51oYRtollNe9e2lA",
        "scope": "GOOGLE",
        "types": [
            "bus_station",
            "transit_station",
            "point_of_interest",
            "establishment"
        ],
        "vicinity": "Tucson"
    },
    {
        "business_status": "OPERATIONAL",
        "geometry": {
            "location": {
                "lat": 32.134008,
                "lng": -111.025113
            },
            "viewport": {
                "northeast": {
                    "lat": 32.1353566302915,
                    "lng": -111.0236932697085
                },
                "southwest": {
                    "lat": 32.13265866970851,
                    "lng": -111.0263912302915
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
        "icon_background_color": "#7B9EB0",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
        "name": "Valencia/Hildreth (NW)",
        "place_id": "ChIJaS9rSKJ51oYRgYN-sWJuMVk",
        "plus_code": {
            "compound_code": "4XMF+JX Drexel Heights, AZ, USA",
            "global_code": "854C4XMF+JX"
        },
        "reference": "ChIJaS9rSKJ51oYRgYN-sWJuMVk",
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
                "lat": 32.133887,
                "lng": -111.029537
            },
            "viewport": {
                "northeast": {
                    "lat": 32.1352365802915,
                    "lng": -111.0282298197085
                },
                "southwest": {
                    "lat": 32.1325386197085,
                    "lng": -111.0309277802915
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
        "icon_background_color": "#7B9EB0",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
        "name": "Valencia at Cardinal",
        "place_id": "ChIJxwo5Z6R51oYRbdHoBdNrQiM",
        "plus_code": {
            "compound_code": "4XMC+H5 Drexel Heights, AZ, USA",
            "global_code": "854C4XMC+H5"
        },
        "reference": "ChIJxwo5Z6R51oYRbdHoBdNrQiM",
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
                "lat": 32.1339842,
                "lng": -111.0212527
            },
            "viewport": {
                "northeast": {
                    "lat": 32.1352937802915,
                    "lng": -111.0199029197085
                },
                "southwest": {
                    "lat": 32.1325958197085,
                    "lng": -111.0226008802915
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
        "icon_background_color": "#7B9EB0",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
        "name": "Valencia/Westover (old Cir. K)",
        "photos": [
            {
                "height": 4032,
                "html_attributions": [
                    "<a href=\"https://maps.google.com/maps/contrib/116985956530143350080\">Norma Perez</a>"
                ],
                "photo_reference": "AdCG2DN2XAl_6UP-CbqFNKMPsXb6_s5VytPR7nUxelZWqO_LNsuidwsl0F3sSQuL7Zv8_OZj4E8F43gEfmTIfqURa2bgrzo019_I2kh4I-whnFMtBCDLsG-CDt5aR5BsfgxtARUHAKsMhbC5cfKB0UubD8u0yHoIEj4N7mbZz2NmfR_EbuGW",
                "width": 3024
            }
        ],
        "place_id": "ChIJV8IAuZh51oYR1dsK4apGOgQ",
        "plus_code": {
            "compound_code": "4XMH+HF Drexel Heights, AZ, USA",
            "global_code": "854C4XMH+HF"
        },
        "rating": 5,
        "reference": "ChIJV8IAuZh51oYR1dsK4apGOgQ",
        "scope": "GOOGLE",
        "types": [
            "transit_station",
            "point_of_interest",
            "establishment"
        ],
        "user_ratings_total": 1,
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