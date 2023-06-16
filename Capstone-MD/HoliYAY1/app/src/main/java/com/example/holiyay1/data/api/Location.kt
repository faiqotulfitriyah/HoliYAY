package com.example.holiyay1.data.api

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Location(
    @field:SerializedName("Place_Id")
    val placeId: Int,
    @field:SerializedName("Place_Name")
    val placeName: String,
    @field:SerializedName("Description")
    val description: String,
    @field:SerializedName("Category")
    val category: String,
    @field:SerializedName("City")
    val city: String,
    @field:SerializedName("Price")
    val price: String,
    @field:SerializedName("Rating")
    val rating: Double,
    @field:SerializedName("Lat")
    val lat: Double,
    @field:SerializedName("Long")
    val long: Double,
    @field:SerializedName("Image")
    val image: String
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(placeId)
        parcel.writeString(placeName)
        parcel.writeString(description)
        parcel.writeString(category)
        parcel.writeString(city)
        parcel.writeString(price)
        parcel.writeDouble(rating)
        parcel.writeDouble(lat)
        parcel.writeDouble(long)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Location> {
        override fun createFromParcel(parcel: Parcel): Location {
            return Location(parcel)
        }

        override fun newArray(size: Int): Array<Location?> {
            return arrayOfNulls(size)
        }
    }
}