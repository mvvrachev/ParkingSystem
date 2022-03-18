package com.example.parkingsystem.ui.home

import android.app.Dialog
import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.parkingsystem.R
import com.example.parkingsystem.models.ParkingSpace
import com.example.parkingsystem.models.Reservation
import com.example.parkingsystem.models.UserInfo
import com.example.parkingsystem.utils.ConfirmReservationDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class ParkingSpacesAdapter(private val todayDate: String,
                           private val tomorrowDate: String) : RecyclerView.Adapter<ParkingSpacesAdapter.ParkingHolder>() {

    private val parkingSpaces : MutableList<ParkingSpace> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.row_parking_space, parent, false)
        return ParkingHolder(layout)
    }

    override fun onBindViewHolder(holder: ParkingHolder, position: Int) {
        val context = holder.parkingSpaceNumber.context
        val spaceNumber = context.getString(R.string.parkingSpace, parkingSpaces[position].floor, parkingSpaces[position].id)
        holder.parkingSpaceNumber.text = spaceNumber
        holder.today.text = todayDate
        holder.tomorrow.text = tomorrowDate

        holder.today.setOnClickListener {
            val dialog = ConfirmReservationDialogFragment(todayDate, parkingSpaces[position].id, spaceNumber)
            val ft = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
            dialog.show(ft, TAG)

//            val auth = Firebase.auth.currentUser
//            val db = Firebase.firestore.collection("user-profiles").document(auth?.uid.toString())
//            val res = Firebase.firestore.collection("reservations")
//            db.get().addOnSuccessListener { d ->
//                if (d != null) {
//                    Log.d(TAG, "DocumentSnapshot data: ${d.data}")
//                    val u = d.toObject<UserInfo>()
//                    val reservation = Reservation(u?.carNumber, todayDate, parkingSpaces[position].id, auth?.uid.toString())
//                    res.add(reservation)
//                } else {
//                    Log.d(TAG, "No such document")
//                }
//            }

        }
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
}