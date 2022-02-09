package com.example.parkingsystem.utils

import android.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.parkingsystem.R

class LoadingDialog(private val fragment: Fragment) {

    private lateinit var dialog: AlertDialog

    fun startLoading() {
        val dialogView = fragment.layoutInflater.inflate(R.layout.loader, null)

        val builder = AlertDialog.Builder(fragment.requireContext())
        builder.setView(dialogView)
            .setCancelable(false)

        dialog = builder.create()
        dialog.show()

    }

    fun dismiss() {
        dialog.dismiss()
    }
}