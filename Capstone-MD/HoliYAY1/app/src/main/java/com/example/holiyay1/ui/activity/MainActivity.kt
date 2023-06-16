package com.example.holiyay1.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.holiyay1.R
import com.example.holiyay1.data.api.ApiConfig
import com.example.holiyay1.data.api.Location
import com.example.holiyay1.data.api.request.RecommendationRequest
import com.example.holiyay1.databinding.ActivityMainBinding
import com.example.holiyay1.ui.LocationAdapter
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var locationAdapter: LocationAdapter
    private var locations: List<Location> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("username")

        binding.profileImage.setImageResource(R.drawable.user_photo)
        binding.greetingText.text = getString(R.string.greeting_message, username)

        binding.homeIcon.setOnClickListener {
            Toast.makeText(this, "You are already on the Home page.", Toast.LENGTH_SHORT).show()
        }

        binding.favoriteIcon.setOnClickListener {
            val favoriteIntent = Intent(this, FavoriteActivity::class.java)
            startActivity(favoriteIntent)
        }

        binding.profileIcon.setOnClickListener {
            val profileIntent = Intent(this, ProfileActivity::class.java)
            startActivity(profileIntent)
        }

        locationAdapter = LocationAdapter()
        locationAdapter.setOnItemClickCallback { location ->
            val detailsIntent = Intent(this, DetailsActivity::class.java).apply {
                putExtra(DetailsActivity.EXTRA_NAME, location.placeName)
                putExtra(DetailsActivity.EXTRA_PHOTO, location.image)
                putExtra(DetailsActivity.EXTRA_DESC, location.description)
            }
            startActivity(detailsIntent)
        }

        binding.popularPlacesRecyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = locationAdapter
        }

        fetchLocations()

        binding.searchButton.setOnClickListener {
            val query = binding.keywordsEdittext.text.toString().trim()
            val city = binding.cityEdittext.text.toString().trim()

            if (query.isNotEmpty() && city.isNotEmpty()) {
                val recommendationRequest = RecommendationRequest(query, city)
                fetchRecommendation(recommendationRequest)
            } else {
                Toast.makeText(this@MainActivity, "Please enter search query and city", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun fetchLocations() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val apiService = ApiConfig.getApiService()
                val response = apiService.getAllLocations()
                if (response.isSuccessful) {
                    val locationResponse = response.body()
                    locationResponse?.let {
                        locations = it.location
                        locationAdapter.setList(locations)
                    }
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Failed to fetch locations: ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(
                    this@MainActivity,
                    "An error occurred: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun fetchRecommendation(request: RecommendationRequest) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val apiService = ApiConfig.getApiService()
                val response = apiService.getRecommendation(request)
                if (response.isSuccessful) {
                    val recommendationResponse = response.body()
                    recommendationResponse?.let {
                        val filteredLocations = it.recommendation

                        val resultsIntent = Intent(this@MainActivity, ResultsActivity::class.java)
                        resultsIntent.putExtra("query", request.keywords)
                        resultsIntent.putExtra("city", request.city)
                        resultsIntent.putExtra("locations", ArrayList(filteredLocations))
                        startActivity(resultsIntent)
                    }
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Failed to fetch recommendation: ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(
                    this@MainActivity,
                    "An error occurred: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}