package com.example.parkingsystem.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.parkingsystem.utils.LoadingDialog

open class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    private lateinit var loader: LoadingDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loader = LoadingDialog(requireContext())
    }

    fun loaderVisible(isVisible: Boolean) {
        if (isVisible) {
            loader.show()
        } else {
            loader.hide()
        }
    }
}