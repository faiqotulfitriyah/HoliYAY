package com.example.holiyay1.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.holiyay1.R

class ProfileActivity : AppCompatActivity() {
    private lateinit var backButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        backButton = findViewById(R.id.back_button)

        backButton.setOnClickListener {
            finish() // Finish the current activity and go back to the previous activity
        }
    }
}