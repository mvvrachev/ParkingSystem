package com.example.parkingsystem.ui.home

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.parkingsystem.R
import com.example.parkingsystem.models.ParkingSpace
import com.google.android.material.chip.Chip
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat

import java.util.*


// when adding new items call notifyDataSetChanged()
// ListAdapter must extend RecyclerView
class ParkingSpacesAdapter : RecyclerView.Adapter<ParkingSpacesAdapter.ParkingHolder>() {

    private val parkingSpaces : MutableList<ParkingSpace> = mutableListOf()
    private lateinit var parkingSpaceText: String

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.row_parking_space, parent, false)
        return ParkingHolder(layout)
    }

    override fun onBindViewHolder(holder: ParkingHolder, position: Int) {
        parkingSpaceText = "Floor: ${parkingSpaces[position].floor} / Space: ${parkingSpaces[position].id}"
        holder.parkingSpaceNumber.text = parkingSpaceText
        holder.today.text = getTodayDate()
        holder.tomorrow.text = getTomorrowDate()

    }

    override fun getItemCount(): Int {
        return parkingSpaces.size
    }

    fun setData(data: List<ParkingSpace>) {
        parkingSpaces.clear()
        parkingSpaces.addAll(data)
        notifyDataSetChanged()
    }

    // TODO: How to do binding gist github
    class ParkingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val parkingSpaceNumber: TextView = itemView.findViewById(R.id.parkingSpaceNumber)
        val today: Chip = itemView.findViewById(R.id.dateOne)
        val tomorrow: Chip = itemView.findViewById(R.id.dateTwo)
    }

    private fun getTodayDate(): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        val calendar = Calendar.getInstance()
        return formatter.format(calendar.time)


    }

    private fun getTomorrowDate(): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, 1)

         return formatter.format(calendar.time)
    }

}