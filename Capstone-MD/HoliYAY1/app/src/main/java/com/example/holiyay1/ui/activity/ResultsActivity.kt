package com.example.holiyay1.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.holiyay1.R
import com.example.holiyay1.data.api.Location
import com.example.holiyay1.databinding.ActivityResultsBinding
import com.example.holiyay1.ui.LocationAdapter

class ResultsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultsBinding
    private lateinit var locationAdapter: LocationAdapter
    private var locations: List<Location> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        val userQuery = intent.getStringExtra("query") ?: ""
        val userCity = intent.getStringExtra("city") ?: ""
        @Suppress("DEPRECATION")
        locations = intent.getParcelableArrayListExtra("locations") ?: emptyList()

        val filteredLocations = filterLocations(userQuery, userCity)

        val firstTextView = binding.firstTextview
        firstTextView.text = getString(R.string.quote)

        val secondTextView = binding.secondTextview
        secondTextView.text = getString(R.string.recommendation)

        val recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(this)
        locationAdapter = LocationAdapter()
        recyclerView.adapter = locationAdapter

        showSearchResults(filteredLocations)
    }

    private fun filterLocations(query: String, city: String): List<Location> {
        val filteredLocations = mutableListOf<Location>()

        for (location in locations) {
            if (location.placeName.contains(query, ignoreCase = true) && location.city.contains(city, ignoreCase = true)) {
                filteredLocations.add(location)
            }
        }
        return filteredLocations
    }

    private fun showSearchResults(locations: List<Location>) {
        locationAdapter.setList(locations)
    }
}