package com.example.parkingsystem.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parkingsystem.R
import com.example.parkingsystem.models.ParkingSpace
import java.time.LocalDate
import java.util.*

// when adding new items call notifyDataSetChanged()
// ListAdapter must extend RecyclerView
class ParkingSpacesAdapter() : RecyclerView.Adapter<ParkingSpacesAdapter.ParkingHolder>() {

    val date = Date()
    private val parkingSpaces : MutableList<ParkingSpace> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.row_parking_space, parent, false)
        return ParkingHolder(layout)
    }

    override fun onBindViewHolder(holder: ParkingHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        parkingSpaces.size
    }

    fun loadDate(data: List<ParkingSpace>) {
        parkingSpaces.clear()
        parkingSpaces.addAll(data)
        notifyDataSetChanged()
    }

    // TODO: How to do binding gist github
    class ParkingHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {


        // ParkingSpace
//        fun bind() {
//
//        }

    }
}