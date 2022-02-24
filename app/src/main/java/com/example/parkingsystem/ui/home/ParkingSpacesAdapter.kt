package com.example.parkingsystem.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.parkingsystem.R
import com.example.parkingsystem.models.ParkingSpace
import com.google.android.material.chip.Chip
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

// when adding new items call notifyDataSetChanged()
// ListAdapter must extend RecyclerView
class ParkingSpacesAdapter() : RecyclerView.Adapter<ParkingSpacesAdapter.ParkingHolder>() {

    private val today = "24/02"
    private val tomorrow = "25/02"

    private val parkingSpaces : MutableList<ParkingSpace> = mutableListOf(
        ParkingSpace("Area -1 / Inside / Space 21"),
        ParkingSpace("Area 0 / Outside / Space 20"),
        ParkingSpace("Area -1 / Inside / Space 1"))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.row_parking_space, parent, false)
        return ParkingHolder(layout)
    }

    override fun onBindViewHolder(holder: ParkingHolder, position: Int) {
        holder.parkingSpaceNumber.text = parkingSpaces[position].name
        holder.today.text = today
        holder.tomorrow.text = tomorrow

    }

    override fun getItemCount(): Int {
        return parkingSpaces.size
    }

    fun loadDate(data: List<ParkingSpace>) {
        parkingSpaces.clear()
        parkingSpaces.addAll(data)
        notifyDataSetChanged()
    }

    // TODO: How to do binding gist github
    class ParkingHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val parkingSpaceNumber: TextView = itemView.findViewById(R.id.parkingSpaceNumber)
        val today: Chip = itemView.findViewById(R.id.dateOne)
        val tomorrow: Chip = itemView.findViewById(R.id.dateTwo)

        // ParkingSpace
//        fun bind() {
//
//        }

    }
}