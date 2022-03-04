package com.example.parkingsystem.ui.home

import android.content.ContentValues
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
class ParkingSpacesAdapter(private val parkingSpaces: MutableList<ParkingSpace>) : RecyclerView.Adapter<ParkingSpacesAdapter.ParkingHolder>() {

    //private val parkingSpaces : MutableList<ParkingSpace> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.row_parking_space, parent, false)
        return ParkingHolder(layout)
    }

    override fun onBindViewHolder(holder: ParkingHolder, position: Int) {
        holder.parkingSpaceNumber.text = "text" //parkingSpaces[0].spaces?.get(position)?.get("id").toString()
        holder.today.text = getTodayDate()
        holder.tomorrow.text = getTomorrowDate()

    }

    override fun getItemCount(): Int {
//        val a = parkingSpaces[0].spaces?.size
//        return a as Int
        return 1

    }

//    fun loadData(data: List<ParkingSpace>) {
//        parkingSpaces.clear()
//        parkingSpaces.addAll(data)
//        notifyDataSetChanged()
//    }

    // TODO: How to do binding gist github
    class ParkingHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
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