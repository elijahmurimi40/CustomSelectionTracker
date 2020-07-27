package com.fortie40.customselectiontracker.helperclasses

import android.view.View
import com.google.android.material.snackbar.Snackbar

object HelperFunctions {
    fun showShortSnackBar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }
}