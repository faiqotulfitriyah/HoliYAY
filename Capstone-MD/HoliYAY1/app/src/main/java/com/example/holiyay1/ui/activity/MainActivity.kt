package com.example.holiyay1.data.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.holiyay1.R

class MainActivity : AppCompatActivity() {
    private lateinit var userPhotoImageView: ImageView
    private lateinit var greetingTextView: TextView
    private lateinit var recyclerView1: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var searchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userPhotoImageView = findViewById(R.id.profile_image)
        greetingTextView = findViewById(R.id.greeting_text)
        recyclerView1 = findViewById(R.id.travel_plannings_recyclerview)
        recyclerView2 = findViewById(R.id.popular_places_recyclerview)
        searchButton = findViewById(R.id.search_button)

        // Retrieve user information from login
        val username = intent.getStringExtra("username")

        // Update UI with user information
        userPhotoImageView.setImageResource(R.drawable.user_photo)
        greetingTextView.text = getString(R.string.greeting_message, username)

        // Set click listener for home_icon
        val homeIcon = findViewById<ImageView>(R.id.home_icon)
        homeIcon.setOnClickListener {
            Toast.makeText(this, "You are already on the Home page.", Toast.LENGTH_SHORT).show()
        }

        // Set click listener for favorite_icon
        val favoriteIcon = findViewById<ImageView>(R.id.favorite_icon)
        favoriteIcon.setOnClickListener {
            val favoriteIntent = Intent(this, FavoriteActivity::class.java)
            startActivity(favoriteIntent)
        }

        // Set click listener for profile_icon
        val profileIcon = findViewById<ImageView>(R.id.profile_icon)
        profileIcon.setOnClickListener {
            val profileIntent = Intent(this, ProfileActivity::class.java)
            startActivity(profileIntent)
        }

        // Set click listener for search_button
        searchButton.setOnClickListener {
            val resultsIntent = Intent(this, ResultsActivity::class.java)
            startActivity(resultsIntent)
        }

        // TODO: Implement logic for populating RecyclerViews with data
    }
}
