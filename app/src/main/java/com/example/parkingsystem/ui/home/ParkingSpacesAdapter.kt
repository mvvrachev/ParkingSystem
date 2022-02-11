package com.example.parkingsystem.ui.home

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

// when adding new items call notifyDataSetChanged()
class ParkingSpacesAdapter(var context: Context) : RecyclerView.Adapter<ParkingSpacesAdapter.ParkingHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ParkingHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    // TODO: How to do binding gist github
    class ParkingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}