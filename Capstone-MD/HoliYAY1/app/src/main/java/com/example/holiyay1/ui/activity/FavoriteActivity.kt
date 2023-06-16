package com.example.holiyay1.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.Toast
import com.example.holiyay1.R

class FavoriteActivity : AppCompatActivity() {
    private lateinit var homeIcon: ImageView
    private lateinit var favoriteIcon: ImageView
    private lateinit var profileIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        homeIcon = findViewById(R.id.home_icon)
        favoriteIcon = findViewById(R.id.favorite_icon)
        profileIcon = findViewById(R.id.profile_icon)

        homeIcon.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        favoriteIcon.setOnClickListener {
            Toast.makeText(this, "You are already in Favorites", Toast.LENGTH_SHORT).show()
        }

        profileIcon.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}