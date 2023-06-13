package com.example.holiyay1.data.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.TextView
import com.example.holiyay1.R

class ResultsActivity : AppCompatActivity() {
    private lateinit var backButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var firstTextView: TextView
    private lateinit var secondTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        backButton = findViewById(R.id.back_button)
        recyclerView = findViewById(R.id.recyclerview)
        firstTextView = findViewById(R.id.first_textview)
        secondTextView = findViewById(R.id.second_textview)

        // Set click listener for the back button
        backButton.setOnClickListener {
            finish() // Finish the current activity and go back to the previous activity
        }

        // TODO: Implement logic for populating RecyclerView with data
    }
}