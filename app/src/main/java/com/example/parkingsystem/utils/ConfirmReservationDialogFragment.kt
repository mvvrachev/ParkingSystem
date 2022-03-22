package com.example.parkingsystem.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.parkingsystem.R
import com.example.parkingsystem.base.DialogClickListener
import com.example.parkingsystem.models.ParkingSpace

class ConfirmReservationDialogFragment(private val date: String,
                                       private val parkingSpace: ParkingSpace,
                                       private val clickCallback: DialogClickListener): DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.confirm_reservation, container, false)

        val areYouSure: TextView = view.findViewById(R.id.are_you_sure)
        areYouSure.text = requireContext().getString(R.string.are_you_sure_you_want_to_reserve_for, date)

        val space: TextView = view.findViewById(R.id.space_id)
        space.text = requireContext().getString(R.string.parkingSpace, parkingSpace.floor, parkingSpace.id)

        val cancelBtn: Button = view.findViewById(R.id.cancel)
        val confirmBtn: Button = view.findViewById(R.id.confirm)

        cancelBtn.setOnClickListener {
            clickCallback.onClick(R.id.cancel, this)
        }
        confirmBtn.setOnClickListener {
            clickCallback.onClick(R.id.confirm, this)
            dismiss()
        }

        return view
    }
}