package com.example.holiyay1.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.holiyay1.data.api.Location
import com.example.holiyay1.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_PHOTO = "extra_photo"
        const val EXTRA_DESC = "extra_description"
        const val EXTRA_CITY = "extra_city"
        const val EXTRA_PRICE = "extra_price"
        const val EXTRA_RATING = "extra_rating"
    }

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val placeName = intent.getStringExtra(EXTRA_NAME)
        val photoUrl = intent.getStringExtra(EXTRA_PHOTO)
        val description = intent.getStringExtra(EXTRA_DESC)
        val city = intent.getStringExtra(EXTRA_CITY)
        val price = intent.getStringExtra(EXTRA_PRICE)
        val rating = intent.getDoubleExtra(EXTRA_RATING, 0.0)

        bindLocationDetails(placeName, photoUrl, description, city, price, rating)
    }

    private fun bindLocationDetails(
        placeName: String?,
        photoUrl: String?,
        description: String?,
        city: String?,
        price: String?,
        rating: Double
    ) {
        binding.apply {
            Glide.with(this@DetailsActivity)
                .load(photoUrl)
                .into(imageView)

            val location = Location(
                placeId = 0, // Set appropriate place ID here
                image = photoUrl ?: "",
                placeName = placeName ?: "",
                description = description ?: "",
                city = city ?: "",
                price = price ?: "",
                rating = rating,
                category = "",
                lat = 0.0,
                long = 0.0
            )

            details = location
            cityTextView.text = location.city
            priceTextView.text = location.price
            ratingTextView.text = location.rating.toString()

            executePendingBindings()
        }
    }
}