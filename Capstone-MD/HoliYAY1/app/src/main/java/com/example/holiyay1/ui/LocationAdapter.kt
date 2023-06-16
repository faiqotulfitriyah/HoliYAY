package com.example.holiyay1.ui

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.holiyay1.data.api.Location
import com.example.holiyay1.databinding.PlacesRowBinding
import com.bumptech.glide.Glide

class LocationAdapter : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    private val list = ArrayList<Location>()
    private var onItemClickCallback: ((Location) -> Unit)? = null

    fun setOnItemClickCallback(callback: (Location) -> Unit) {
        onItemClickCallback = callback
    }

    fun setList(locations: List<Location>) {
        list.clear()
        list.addAll(locations)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding = PlacesRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location = list[position]
        holder.bind(location)
        holder.itemView.setOnClickListener {
            onItemClickCallback?.invoke(location)
        }
    }

    override fun getItemCount(): Int = list.size

    inner class LocationViewHolder(private val binding: PlacesRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(location: Location) {
            binding.apply {
                Glide.with(itemView)
                    .load(location.image)
                    .into(photo)
                tvname.text = location.placeName
                description.text = location.description
                description.apply {
                    maxLines = 2
                    ellipsize = TextUtils.TruncateAt.END
                }
            }
        }
    }
}