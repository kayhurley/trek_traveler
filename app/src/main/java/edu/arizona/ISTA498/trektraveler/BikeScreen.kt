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
            },
            "viewport": {
                "northeast": {
                    "lat": 32.15860657989273,
                    "lng": -111.0211520201073
                },
                "southwest": {
                    "lat": 32.15590692010728,
                    "lng": -111.0238516798927
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/park-71.png",
        "icon_background_color": "#4DB546",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/tree_pinlet",
        "name": "Winston Reynolds - Manzanita Park",
        "photos": [
            {
                "height": 4096,
                "html_attributions": [
                    "<a href=\"https://maps.google.com/maps/contrib/109610250907554081982\">David Peaslee</a>"
                ],
                "photo_reference": "AdCG2DMnyMCqRQPlyG3D8asJMmnLuIpnIoA-e3ic5z8T1M8s0YnabLE7W-ajOglV5HxQMWFWVstyG7OuAbUjKrCao_lhwoFJ5PPnQdxtQ4PLP-Gzz9E62isCbOjAJxM8rsXM8gBGWByzzpmnMKU3pJBmsSgSTQpiYpH1iQChfzBZf3K9OSWR",
                "width": 3072
            }
        ],
        "place_id": "ChIJvS44gMJ51oYRcMOHTwKmYmg",
        "plus_code": {
            "compound_code": "5X4H+W2 Tucson, Arizona",
            "global_code": "854C5X4H+W2"
        },
        "rating": 4.6,
        "reference": "ChIJvS44gMJ51oYRcMOHTwKmYmg",
        "scope": "GOOGLE",
        "types": [
            "park",
            "tourist_attraction",
            "point_of_interest",
            "establishment"
        ],
        "user_ratings_total": 659,
        "vicinity": "5200 S Westover Ave, Tucson"
    },
    {
        "business_status": "OPERATIONAL",
        "geometry": {
            "location": {
                "lat": 32.1784054,
                "lng": -110.9514553
            },
            "viewport": {
                "northeast": {
                    "lat": 32.17964822989272,
                    "lng": -110.9499714201073
                },
                "southwest": {
                    "lat": 32.17694857010728,
                    "lng": -110.9526710798927
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/shopping-71.png",
        "icon_background_color": "#4B96F3",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/shopping_pinlet",
        "name": "Ajo Bikes",
        "opening_hours": {
            "open_now": true
        },
        "photos": [
            {
                "height": 3072,
                "html_attributions": [
                    "<a href=\"https://maps.google.com/maps/contrib/116565735029084260597\">Kris Bjelk</a>"
                ],
                "photo_reference": "AdCG2DNrRuGkzHmRnFwjZagKkGlVn8vIhvBlRAwCewAt-gVNSSDob8WFbGZCjKo4ceh_BBpEInPcty9xNFHDh6XZzajcB01IzIlplgUh8GjUJD8hfGTTAkMC65FirZQ-TxwQRb6Rj2521DKwIs1mKQt15E9QiLwPbBUM4xk6N68dd4cY34dA",
                "width": 4080
            }
        ],
        "place_id": "ChIJ3XkIc3561oYREWGVi5l2vHQ",
        "plus_code": {
            "compound_code": "52HX+9C Tucson, Arizona",
            "global_code": "854F52HX+9C"
        },
        "rating": 4.8,
        "reference": "ChIJ3XkIc3561oYREWGVi5l2vHQ",
        "scope": "GOOGLE",
        "types": [
            "bicycle_store",
            "store",
            "point_of_interest",
            "establishment"
        ],
        "user_ratings_total": 622,
        "vicinity": "1301 E Ajo Way #117, Tucson"
    },
    {
        "business_status": "OPERATIONAL",
        "geometry": {
            "location": {
                "lat": 32.1777929,
                "lng": -110.8406468
            },
            "viewport": {
                "northeast": {
                    "lat": 32.17913477989271,
                    "lng": -110.8393230201073
                },
                "southwest": {
                    "lat": 32.17643512010727,
                    "lng": -110.8420226798927
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
        "icon_background_color": "#7B9EB0",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
        "name": "Tucson Adventure Rentals",
        "opening_hours": {
            "open_now": true
        },
        "photos": [
            {
                "height": 2852,
                "html_attributions": [
                    "<a href=\"https://maps.google.com/maps/contrib/108099683749806414873\">A Google User</a>"
                ],
                "photo_reference": "AdCG2DPKZVMc0A4vGLxpR_GUEUdmuwATixWDK9XtKQQM2XpCBOBSXs7-qjiul6aCcPz-wKrIVWEvvMPUtZregmSC3RoHUGqAMo9osUYjUHjZSCmw_Kb4EVJybc4oSZv8Gr_ezutZT-GWzpQ5D-vRwlP1azxcXvyd_PP3srHEFxLlKQduSevX",
                "width": 4306
            }
        ],
        "place_id": "ChIJDbEGLJZh1oYRQd2fGwgQ_fI",
        "plus_code": {
            "compound_code": "55H5+4P Tucson, Arizona",
            "global_code": "854F55H5+4P"
        },
        "rating": 4.7,
        "reference": "ChIJDbEGLJZh1oYRQd2fGwgQ_fI",
        "scope": "GOOGLE",
        "types": [
            "travel_agency",
            "point_of_interest",
            "establishment"
        ],
        "user_ratings_total": 79,
        "vicinity": "3797 S Kolb Rd, Tucson"
    },
    {
        "business_status": "OPERATIONAL",
        "geometry": {
            "location": {
                "lat": 32.1777929,
                "lng": -110.8406468
            },
            "viewport": {
                "northeast": {
                    "lat": 32.17914317989272,
                    "lng": -110.8394075201073
                },
                "southwest": {
                    "lat": 32.17644352010727,
                    "lng": -110.8421071798927
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
        "icon_background_color": "#7B9EB0",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
        "name": "Tucson RZR Rental",
        "opening_hours": {
            "open_now": true
        },
        "photos": [
            {
                "height": 3110,
                "html_attributions": [
                    "<a href=\"https://maps.google.com/maps/contrib/109409176764721510018\">A Google User</a>"
                ],
                "photo_reference": "AdCG2DP7zLWxtfNSZN9C5OIiwaGv8F9bJgZsAjpj_bxmFobJdQJ1GzfgFKFaM4X03P1FkfNiMYtx3OudNQ_a0naBsP-thgqU51qPUBxgrGfZKUYCW6Vm85C9PNTIzmJi6-seZkk-hxZvbco4nJCHcxpFk89ikACtiMu8uRrmuUsHROhGnrpN",
                "width": 4697
            }
        ],
        "place_id": "ChIJO3RqOIRv1oYREXn3bdOjrFg",
        "plus_code": {
            "compound_code": "55H5+4P Tucson, Arizona",
            "global_code": "854F55H5+4P"
        },
        "rating": 4.6,
        "reference": "ChIJO3RqOIRv1oYREXn3bdOjrFg",
        "scope": "GOOGLE",
        "types": [
            "travel_agency",
            "point_of_interest",
            "establishment"
        ],
        "user_ratings_total": 24,
        "vicinity": "3797 S Kolb Rd, Tucson"
    },
    {
        "business_status": "OPERATIONAL",
        "geometry": {
            "location": {
                "lat": 32.196318,
                "lng": -110.9687777
            },
            "viewport": {
                "northeast": {
                    "lat": 32.19766842989272,
                    "lng": -110.9672735701073
                },
                "southwest": {
                    "lat": 32.19496877010727,
                    "lng": -110.9699732298927
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
        "icon_background_color": "#7B9EB0",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
        "name": "Sky Island Bikes",
        "photos": [
            {
                "height": 3024,
                "html_attributions": [
                    "<a href=\"https://maps.google.com/maps/contrib/109910166319311638267\">A Google User</a>"
                ],
                "photo_reference": "AdCG2DM0WHsgyeVjgLFN3KXvG0g_EqVGtztYh6mjq-YYhI5XBfDuHmghiXtzxLLmoG8P6bfMH-JeHjQakmiBbi_7exg8RuLFNtfJPKRmq05TX811eC8H2t7zoNSHqTYFoixzukNkSN6kuGxlgiwkdwsQLR-WaAABbZkcuPbA1Y1PGwnAi8SH",
                "width": 4032
            }
        ],
        "place_id": "ChIJr1_RV8hx1oYRmN2_aYWEk5o",
        "plus_code": {
            "compound_code": "52WJ+GF Tucson, Arizona",
            "global_code": "854F52WJ+GF"
        },
        "rating": 5,
        "reference": "ChIJr1_RV8hx1oYRmN2_aYWEk5o",
        "scope": "GOOGLE",
        "types": [
            "point_of_interest",
            "establishment"
        ],
        "user_ratings_total": 30,
        "vicinity": "2210 S 6th Ave Ste 5, Tucson"
    },
    {
        "business_status": "OPERATIONAL",
        "geometry": {
            "location": {
                "lat": 32.1899492,
                "lng": -110.8214673
            },
            "viewport": {
                "northeast": {
                    "lat": 32.19137257989271,
                    "lng": -110.8201234701073
                },
                "southwest": {
                    "lat": 32.18867292010727,
                    "lng": -110.8228231298927
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
        "icon_background_color": "#7B9EB0",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
        "name": "Tucson Twisted Cycles",
        "opening_hours": {
            "open_now": true
        },
        "photos": [
            {
                "height": 3024,
                "html_attributions": [
                    "<a href=\"https://maps.google.com/maps/contrib/104259765893355237128\">Benjamin Flores</a>"
                ],
                "photo_reference": "AdCG2DMtJYf_NpotSmPCFA24PcOaXrZLHGEywOIDaSVfM4RpqtD3NolleeJV2bM1iS6_VaXCOs3YXYeqCUGMfkWhZWGga367itMhCBmChrPX9Wh42Q-6ZXF5LpDj4rSMAbi8LQ3-QWmxia9sZfIZ9CE8NYCZIEoBvKZtzOAxuaA8j995eys6",
                "width": 4032
            }
        ],
        "place_id": "ChIJJ61v18xv1oYRb_HbNzGPVBM",
        "plus_code": {
            "compound_code": "55QH+XC Tucson, Arizona",
            "global_code": "854F55QH+XC"
        },
        "rating": 4.4,
        "reference": "ChIJJ61v18xv1oYRb_HbNzGPVBM",
        "scope": "GOOGLE",
        "types": [
            "car_repair",
            "store",
            "point_of_interest",
            "establishment"
        ],
        "user_ratings_total": 89,
        "vicinity": "7941 E Lakeside Pkwy Suite 111, Tucson"
    },
    {
        "business_status": "OPERATIONAL",
        "geometry": {
            "location": {
                "lat": 32.2197249,
                "lng": -110.9851665
            },
            "viewport": {
                "northeast": {
                    "lat": 32.22106857989272,
                    "lng": -110.9838651201073
                },
                "southwest": {
                    "lat": 32.21836892010728,
                    "lng": -110.9865647798927
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
        "icon_background_color": "#7B9EB0",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
        "name": "TUGO Bikeshare Station: North Mercado",
        "opening_hours": {
            "open_now": true
        },
        "photos": [
            {
                "height": 3036,
                "html_attributions": [
                    "<a href=\"https://maps.google.com/maps/contrib/104856225862699124994\">Matthew Bernier</a>"
                ],
                "photo_reference": "AdCG2DNDqnf4Q8vVMsSIJ3iSw7u4MIhiW8MKe-hLX8tAAWnsP--6mWKxTUp7O4ZOi8DU5P0A_HjLPqGlc5o2ErrV-bYJX8ZystIcWASgQj784o3QwMDMh-YhyXzB5vR6MW8Gb6wkI97CwlElFsS8eLjueGQB_RXqL4cDTUOCBYPqxt5szCwy",
                "width": 4048
            }
        ],
        "place_id": "ChIJP4tSEthw1oYRLGq-04138Uc",
        "plus_code": {
            "compound_code": "6297+VW Tucson, Arizona",
            "global_code": "854F6297+VW"
        },
        "rating": 5,
        "reference": "ChIJP4tSEthw1oYRLGq-04138Uc",
        "scope": "GOOGLE",
        "types": [
            "point_of_interest",
            "establishment"
        ],
        "user_ratings_total": 1,
        "vicinity": "Tucson"
    },
    {
        "business_status": "OPERATIONAL",
        "geometry": {
            "location": {
                "lat": 32.2177056,
                "lng": -110.9844144
            },
            "viewport": {
                "northeast": {
                    "lat": 32.21906087989272,
                    "lng": -110.9830656701073
                },
                "southwest": {
                    "lat": 32.21636122010727,
                    "lng": -110.9857653298927
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
        "icon_background_color": "#7B9EB0",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
        "name": "TUGO Bikeshare Station: South Mercado",
        "opening_hours": {
            "open_now": true
        },
        "photos": [
            {
                "height": 3036,
                "html_attributions": [
                    "<a href=\"https://maps.google.com/maps/contrib/104856225862699124994\">Matthew Bernier</a>"
                ],
                "photo_reference": "AdCG2DMxWlX_IqERhYFqCiqCdLifXNj11Xt0NHLaKG8QBgZp6PmOlYykv8AFnRShwOlGJM_AvgLorNWPcXQkL9qWdi3uZwjwscDgZstPRv5STKpnB5_DsVdJSl32nz62PPTFJJMfcZVNKou1_zxrsjcwnw0fao0YH5jyEIeWo5BE361T-t-q",
                "width": 4048
            }
        ],
        "place_id": "ChIJjdbOs9lw1oYR3TaecFkm1gU",
        "plus_code": {
            "compound_code": "6298+36 Tucson, Arizona",
            "global_code": "854F6298+36"
        },
        "rating": 5,
        "reference": "ChIJjdbOs9lw1oYR3TaecFkm1gU",
        "scope": "GOOGLE",
        "types": [
            "point_of_interest",
            "establishment"
        ],
        "user_ratings_total": 1,
        "vicinity": "777 W Cushing St, Tucson"
    },
    {
        "business_status": "OPERATIONAL",
        "geometry": {
            "location": {
                "lat": 32.2177803,
                "lng": -110.9845871
            },
            "viewport": {
                "northeast": {
                    "lat": 32.21911142989271,
                    "lng": -110.9833568201073
                },
                "southwest": {
                    "lat": 32.21641177010727,
                    "lng": -110.9860564798927
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/shopping-71.png",
        "icon_background_color": "#4B96F3",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/shopping_pinlet",
        "name": "Transit Cycles",
        "opening_hours": {
            "open_now": true
        },
        "photos": [
            {
                "height": 1365,
                "html_attributions": [
                    "<a href=\"https://maps.google.com/maps/contrib/103806688542964029399\">Transit Cycles</a>"
                ],
                "photo_reference": "AdCG2DN5MZqesFQ7CdJrLPBFkqN02rldntFOEfNNjnIGUL7S6YuAZJZmtalKMFrJs-ocR7i_qlVcVnvVv4IQOHMCO8GqWgBQ5tCDnGQWS7D6PzzAjBO4WhPdzV2PFnySCLlYNYfeD_gxxzNuM3xHouVoEbhscVu8_EBXxd_nzRJoByJna98L",
                "width": 2048
            }
        ],
        "place_id": "ChIJC-90D9hw1oYRlENvUvX01Cg",
        "plus_code": {
            "compound_code": "6298+45 Tucson, Arizona",
            "global_code": "854F6298+45"
        },
        "rating": 4.8,
        "reference": "ChIJC-90D9hw1oYRlENvUvX01Cg",
        "scope": "GOOGLE",
        "types": [
            "bicycle_store",
            "tourist_attraction",
            "store",
            "point_of_interest",
            "establishment"
        ],
        "user_ratings_total": 111,
        "vicinity": "267 South Avenida del Convento Bldg 10, Tucson"
    },
    {
        "business_status": "OPERATIONAL",
        "geometry": {
            "location": {
                "lat": 32.2087373,
                "lng": -110.9746779
            },
            "viewport": {
                "northeast": {
                    "lat": 32.21008712989271,
                    "lng": -110.9733280701073
                },
                "southwest": {
                    "lat": 32.20738747010727,
                    "lng": -110.9760277298927
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
        "icon_background_color": "#7B9EB0",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
        "name": "TUGO Bikeshare Station: Santa Rosa",
        "opening_hours": {
            "open_now": true
        },
        "photos": [
            {
                "height": 3036,
                "html_attributions": [
                    "<a href=\"https://maps.google.com/maps/contrib/104856225862699124994\">Matthew Bernier</a>"
                ],
                "photo_reference": "AdCG2DMe3bWMTwh9FnW_EN0qxxpWENuOxM9OVI1hwZHjZbs3TlbZGPp2TopRYSfqtc9JaRS6R1q-6B1OBJ6XZ_Azr4c51n91B8S4L-Zj-G2IxA_paaNeplsTUM6AUcT0acx50YnFkXdC1Kk0yB-C68vGAU7gpd_JnmlNtCHDfLKRWZy9fP3y",
                "width": 4048
            }
        ],
        "place_id": "ChIJJejCnLtx1oYRYqGYqPR-euU",
        "plus_code": {
            "compound_code": "625G+F4 Tucson, Arizona",
            "global_code": "854F625G+F4"
        },
        "rating": 5,
        "reference": "ChIJJejCnLtx1oYRYqGYqPR-euU",
        "scope": "GOOGLE",
        "types": [
            "point_of_interest",
            "establishment"
        ],
        "user_ratings_total": 1,
        "vicinity": "519-401 W La Paz St, Tucson"
    },
    {
        "business_status": "OPERATIONAL",
        "geometry": {
            "location": {
                "lat": 32.2197732,
                "lng": -110.9683023
            },
            "viewport": {
                "northeast": {
                    "lat": 32.22117452989272,
                    "lng": -110.9669530201073
                },
                "southwest": {
                    "lat": 32.21847487010728,
                    "lng": -110.9696526798927
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
        "icon_background_color": "#7B9EB0",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
        "name": "TUGO Bikeshare Station: Armory Park",
        "opening_hours": {
            "open_now": true
        },
        "photos": [
            {
                "height": 3036,
                "html_attributions": [
                    "<a href=\"https://maps.google.com/maps/contrib/104856225862699124994\">Matthew Bernier</a>"
                ],
                "photo_reference": "AdCG2DNGEKeEQ4GA1oxDZ2f0oQJPpoiiMb8zRhye56sbLB636cThUxWW9eiK33zCfkKy2uMPawQQ7wCRYGPQ5z8BFmXepT2JGD7jOvi-AIWkQPAhOANnUIy0iD4sEoF9QRLKMhQb-WQm_x1UfbGQEdSFBAzQFCEIcZaH_C-nfUw6Xznm-zOt",
                "width": 4048
            }
        ],
        "place_id": "ChIJS_lS-Slx1oYRmehGqYu0jXY",
        "plus_code": {
            "compound_code": "629J+WM Tucson, Arizona",
            "global_code": "854F629J+WM"
        },
        "rating": 5,
        "reference": "ChIJS_lS-Slx1oYRmehGqYu0jXY",
        "scope": "GOOGLE",
        "types": [
            "point_of_interest",
            "establishment"
        ],
        "user_ratings_total": 1,
        "vicinity": "127-101 E 12th St, Tucson"
    },
    {
        "business_status": "OPERATIONAL",
        "geometry": {
            "location": {
                "lat": 32.2181208,
                "lng": -110.9723988
            },
            "viewport": {
                "northeast": {
                    "lat": 32.21924977989273,
                    "lng": -110.9714649701073
                },
                "southwest": {
                    "lat": 32.21655012010729,
                    "lng": -110.9741646298927
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
        "icon_background_color": "#7B9EB0",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
        "name": "TUGO Bikeshare Station: Tucson Convention Center",
        "opening_hours": {
            "open_now": true
        },
        "photos": [
            {
                "height": 4160,
                "html_attributions": [
                    "<a href=\"https://maps.google.com/maps/contrib/110543308211430347888\">A Google User</a>"
                ],
                "photo_reference": "AdCG2DOa916Hf2Pv_3DHFwuGQLSM4Butr0XsVamMvFrRgwk8sQgZaV5SUx48iEjxCDc7_eNlD7PIijW7tmxxCa_V1nhenchgXslBGBnSSqGcIWL-J5VlapSF_VQyTe9G_Tw6JbLayKesAFGiIerndm0KvcI_AzxwftJTkzSs_Kn8D4X025vb",
                "width": 3120
            }
        ],
        "place_id": "ChIJvUuD1jxx1oYRzF8LpJdZESc",
        "plus_code": {
            "compound_code": "629H+62 Tucson, Arizona",
            "global_code": "854F629H+62"
        },
        "rating": 5,
        "reference": "ChIJvUuD1jxx1oYRzF8LpJdZESc",
        "scope": "GOOGLE",
        "types": [
            "point_of_interest",
            "establishment"
        ],
        "user_ratings_total": 2,
        "vicinity": "289-271 S Church Ave, Tucson"
    },
    {
        "business_status": "OPERATIONAL",
        "geometry": {
            "location": {
                "lat": 32.2212028,
                "lng": -110.9724792
            },
            "viewport": {
                "northeast": {
                    "lat": 32.22255282989271,
                    "lng": -110.9711306201073
                },
                "southwest": {
                    "lat": 32.21985317010727,
                    "lng": -110.9738302798927
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
        "icon_background_color": "#7B9EB0",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
        "name": "TUGO Bikeshare Station: Congress St & Church Ave",
        "opening_hours": {
            "open_now": true
        },
        "photos": [
            {
                "height": 4160,
                "html_attributions": [
                    "<a href=\"https://maps.google.com/maps/contrib/110543308211430347888\">A Google User</a>"
                ],
                "photo_reference": "AdCG2DOEHcmBdJN2xPeBBbmQ-O-BrlSSkE2IjZD_QqI6uxFKjcmFJH6NESQv_kj4GuMlXG6ZQwmMyapH-vl-QeKbq2ZthqtjpjOTkjHurwapuirJTUxsWdbHYSHAZDn4A5V0dil3nVWaIiZ3cXTkyCDNTvAJc_tC9sAzO8VluVX9lnfCVnDG",
                "width": 3120
            }
        ],
        "place_id": "ChIJGx0y1RRx1oYRzK1l496Cfbg",
        "plus_code": {
            "compound_code": "62CH+F2 Tucson, Arizona",
            "global_code": "854F62CH+F2"
        },
        "rating": 5,
        "reference": "ChIJGx0y1RRx1oYRzK1l496Cfbg",
        "scope": "GOOGLE",
        "types": [
            "point_of_interest",
            "establishment"
        ],
        "user_ratings_total": 2,
        "vicinity": "1-59 W Congress St, Tucson"
    },
    {
        "business_status": "OPERATIONAL",
        "geometry": {
            "location": {
                "lat": 32.2121116,
                "lng": -110.9689671
            },
            "viewport": {
                "northeast": {
                    "lat": 32.21342662989272,
                    "lng": -110.9676171201073
                },
                "southwest": {
                    "lat": 32.21072697010727,
                    "lng": -110.9703167798927
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
        "icon_background_color": "#7B9EB0",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
        "name": "TUGO Bikeshare Station: Five Points",
        "opening_hours": {
            "open_now": true
        },
        "photos": [
            {
                "height": 3036,
                "html_attributions": [
                    "<a href=\"https://maps.google.com/maps/contrib/104856225862699124994\">Matthew Bernier</a>"
                ],
                "photo_reference": "AdCG2DP2pngmBvheXHQtp3WoJU-e1l_60F__YE4R_Aoo94_626dmegw0gQFvNP9gIM4OR-S714uLHot9hOK9_o41tLe0dkj9NR4LFYj4Y_437QaCIS4jGGpdqn7xKOsCeArkW_RYLV9NYtPPZwGyPOA31-t5-Zvv96em4U4EQ6nczAqNUv_m",
                "width": 4048
            }
        ],
        "place_id": "ChIJZUisQ9Nx1oYRIHIVZQPUJxI",
        "plus_code": {
            "compound_code": "626J+RC Tucson, Arizona",
            "global_code": "854F626J+RC"
        },
        "rating": 5,
        "reference": "ChIJZUisQ9Nx1oYRIHIVZQPUJxI",
        "scope": "GOOGLE",
        "types": [
            "point_of_interest",
            "establishment"
        ],
        "user_ratings_total": 1,
        "vicinity": "2-16 W 18th St, Tucson"
    },
    {
        "business_status": "OPERATIONAL",
        "geometry": {
            "location": {
                "lat": 32.2028373,
                "lng": -110.9683642
            },
            "viewport": {
                "northeast": {
                    "lat": 32.20418687989272,
                    "lng": -110.9670425701073
                },
                "southwest": {
                    "lat": 32.20148722010727,
                    "lng": -110.9697422298927
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
        "icon_background_color": "#7B9EB0",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
        "name": "TUGO Station",
        "photos": [
            {
                "height": 3036,
                "html_attributions": [
                    "<a href=\"https://maps.google.com/maps/contrib/104856225862699124994\">Matthew Bernier</a>"
                ],
                "photo_reference": "AdCG2DPqBFYKsIrLYRx9dTAH3Ryy9D9mpy6vvlJYdejNIunC7l0Zn5krZwVILZkBuE1dNh2FhPvUr4R6qzIxb3cdC1iF-ML_5ybtNNcBV-fDQV75ioDiFdrjKBHOsROCRpk84dCCPCYdsgwZV2oxzjFL2kLcf7TjUqZwlaLIhOL2utZtWszc",
                "width": 4048
            }
        ],
        "place_id": "ChIJ_4BHHnJx1oYRfYB2Aru7BmU",
        "plus_code": {
            "compound_code": "623J+4M Tucson, Arizona",
            "global_code": "854F623J+4M"
        },
        "rating": 5,
        "reference": "ChIJ_4BHHnJx1oYRfYB2Aru7BmU",
        "scope": "GOOGLE",
        "types": [
            "point_of_interest",
            "establishment"
        ],
        "user_ratings_total": 1,
        "vicinity": "Tucson"
    },
    {
        "business_status": "OPERATIONAL",
        "geometry": {
            "location": {
                "lat": 32.2064443,
                "lng": -110.9657282
            },
            "viewport": {
                "northeast": {
                    "lat": 32.20779627989273,
                    "lng": -110.9642715201073
                },
                "southwest": {
                    "lat": 32.20509662010728,
                    "lng": -110.9669711798927
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/shopping-71.png",
        "icon_background_color": "#4B96F3",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/shopping_pinlet",
        "name": "Tucson Bicycle Service",
        "opening_hours": {
            "open_now": true
        },
        "photos": [
            {
                "height": 3006,
                "html_attributions": [
                    "<a href=\"https://maps.google.com/maps/contrib/109341548926522369024\">Mario Lizarazu</a>"
                ],
                "photo_reference": "AdCG2DPvxqL1lnI4b1dzHBz6BZbNvoJB1_yifku3MgOs4GZ1iap3QqNdNB994Y3ZGDvm0FgRchrJ2tIKafrEld-VbD1DPsbLXMt5goyYGrBeiBKXo_9AUlFa-3vqov7N_51fAQR73kupS_Qo3f31NsB504ovJvRkI-oijPYIeQeGFucaIJ5x",
                "width": 5344
            }
        ],
        "place_id": "ChIJ_aHQke5w1oYRlweEeUdVeM8",
        "plus_code": {
            "compound_code": "624M+HM Tucson, Arizona",
            "global_code": "854F624M+HM"
        },
        "rating": 4.7,
        "reference": "ChIJ_aHQke5w1oYRlweEeUdVeM8",
        "scope": "GOOGLE",
        "types": [
            "bicycle_store",
            "point_of_interest",
            "store",
            "establishment"
        ],
        "user_ratings_total": 104,
        "vicinity": "248 E 22nd St Suite 150, Tucson"
    },
    {
        "business_status": "OPERATIONAL",
        "geometry": {
            "location": {
                "lat": 32.2217898,
                "lng": -110.968908
            },
            "viewport": {
                "northeast": {
                    "lat": 32.22324497989273,
                    "lng": -110.9675675701073
                },
                "southwest": {
                    "lat": 32.22054532010728,
                    "lng": -110.9702672298927
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/generic_business-71.png",
        "icon_background_color": "#13B5C7",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/generic_pinlet",
        "name": "The Tucson Loop",
        "opening_hours": {
            "open_now": true
        },
        "photos": [
            {
                "height": 5712,
                "html_attributions": [
                    "<a href=\"https://maps.google.com/maps/contrib/103237936739910726037\">Lisa Robinson</a>"
                ],
                "photo_reference": "AdCG2DNCEyKGEXie-D9-aQE7ComGVQFmkRCdPNZSDXw4KzOydbCvjND851Kj6GCVvMCBkMDgWIaFmhTq_q2IUggDFii5T-d0zEye26CY5bYjsL9bYa0AqnEg6GxJpqWVQN4PHfXi6zMW54WMy0pPSRjf30v16xY1Hf1IT3gGkCO1XFT0JoAm",
                "width": 4284
            }
        ],
        "place_id": "ChIJjQ1gAzFx1oYRaVABcU1Oihc",
        "plus_code": {
            "compound_code": "62CJ+PC Tucson, Arizona",
            "global_code": "854F62CJ+PC"
        },
        "rating": 5,
        "reference": "ChIJjQ1gAzFx1oYRaVABcU1Oihc",
        "scope": "GOOGLE",
        "types": [
            "tourist_attraction",
            "travel_agency",
            "point_of_interest",
            "establishment"
        ],
        "user_ratings_total": 4,
        "vicinity": "130 E Congress St Suite 200-116, Tucson"
    },
    {
        "business_status": "OPERATIONAL",
        "geometry": {
            "location": {
                "lat": 32.2072826,
                "lng": -110.8932213
            },
            "viewport": {
                "northeast": {
                    "lat": 32.20862622989272,
                    "lng": -110.8919452701073
                },
                "southwest": {
                    "lat": 32.20592657010727,
                    "lng": -110.8946449298927
                }
            }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/shopping-71.png",
        "icon_background_color": "#4B96F3",
        "icon_mask_base_uri": "https://maps.gstatic.com/mapfiles/place_api/icons/v2/shopping_pinlet",
        "name": "Cycles, Ski, & Atv's",
        "opening_hours": {
            "open_now": true
        },
        "photos": [
            {
                "height": 1187,
                "html_attributions": [
                    "<a href=\"https://maps.google.com/maps/contrib/114106492143869681562\">CSA Super Store Tucson</a>"
                ],
                "photo_reference": "AdCG2DMQuQFHQ3wivfPs1rDsv9y_jFTIw_mYr_d3HaTudLXtBeHkoU6iTZ8OIHHT3Nr5ekU-nuLU2dl7u5kbXaP2sAi_CDdnqpTGCEzqG7_CyYYNGUf2CGQGwEw0YQRGI8GyOeOOCW-T1boHmNzucQSDMsYH9Q6CfYb2zmIi_u2cK92ulgi3",
                "width": 2048
            }
        ],
        "place_id": "ChIJ-xTt6mNv1oYRKsfa-FD7y6w",
        "plus_code": {
            "compound_code": "6444+WP Tucson, Arizona",
            "global_code": "854F6444+WP"
        },
        "rating": 4.3,
        "reference": "ChIJ-xTt6mNv1oYRKsfa-FD7y6w",
        "scope": "GOOGLE",
        "types": [
            "car_repair",
            "store",
            "point_of_interest",
            "establishment"
        ],
        "user_ratings_total": 498,
        "vicinity": "4649 E 22nd St, Tucson"
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