package com.example.parkingsystem.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parkingsystem.R
import com.example.parkingsystem.models.ParkingSpace

// when adding new items call notifyDataSetChanged()
// ListAdapter must extends RecyclerView
class ParkingSpacesAdapter() : RecyclerView.Adapter<ParkingSpacesAdapter.ParkingHolder>() {

    private val parkingSpaces : MutableList<ParkingSpace> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingHolder {
        TODO("Not yet implemented")
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.row_parking_space, parent, false)
    }

    override fun onBindViewHolder(holder: ParkingHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    fun loadDate(data: List<ParkingSpace>) {
        parkingSpaces.clear()
        parkingSpaces.addAll(data)
        notifyDataSetChanged()
    }

    // TODO: How to do binding gist github
    class ParkingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // ParkingSpace
        fun bind() {

        }

    }
}