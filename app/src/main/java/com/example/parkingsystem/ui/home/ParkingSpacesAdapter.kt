package com.example.parkingsystem.ui.home

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
//import com.google.firebase.firestore.ktx.firestore
//import com.google.firebase.ktx.Firebase

// when adding new items call notifyDataSetChanged()
// ListAdapter must extend RecyclerView
class ParkingSpacesAdapter() : RecyclerView.Adapter<ParkingSpacesAdapter.ParkingHolder>() {

//    private val db = Firebase.firestore

    private val today = "24/02"
    private val tomorrow = "25/02"

    private val parkingSpaces : MutableList<ParkingSpace> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.row_parking_space, parent, false)
        return ParkingHolder(layout)
    }

    override fun onBindViewHolder(holder: ParkingHolder, position: Int) {
        //holder.parkingSpaceNumber.text = parkingSpaces[position].name
        holder.today.text = today
        holder.tomorrow.text = tomorrow

    }

    override fun getItemCount(): Int {
        return parkingSpaces.size
    }

    fun loadData(data: List<ParkingSpace>) {
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

//    fun readData() {
//        db.collection("parking-management-system")
//            .get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    Log.d(TAG, "${document.id} => ${document.data}")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.w(TAG, "Error getting documents.", exception)
//            }
//    }
}