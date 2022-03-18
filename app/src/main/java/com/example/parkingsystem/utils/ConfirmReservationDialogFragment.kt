package com.example.parkingsystem.utils

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.parkingsystem.R
import com.example.parkingsystem.models.Reservation
import com.example.parkingsystem.models.UserInfo
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class ConfirmReservationDialogFragment(private val date: String, private val id: Long?, private val spaceString: String): DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.confirm_reservation, container, false)

        var sure: TextView = view.findViewById(R.id.are_you_sure)
        sure.text = requireContext().getString(R.string.are_you_sure_you_want_to_reserve_for, date)

        var space: TextView = view.findViewById(R.id.space_id)
        space.text = spaceString

        var cancelBtn: Button = view.findViewById(R.id.cancel)
        var confirmBtn: Button = view.findViewById(R.id.confirm)
        cancelBtn.setOnClickListener {
            dismiss()
        }
        confirmBtn.setOnClickListener {
            val auth = Firebase.auth.currentUser
            val db = Firebase.firestore.collection("user-profiles").document(auth?.uid.toString())
            val res = Firebase.firestore.collection("reservations")
            db.get().addOnSuccessListener { d ->
                if (d != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${d.data}")
                    val u = d.toObject<UserInfo>()
                    val reservation = Reservation(u?.carNumber, date, id, auth?.uid.toString())
                    res.add(reservation)
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            dismiss()
        }

        return view
    }
}