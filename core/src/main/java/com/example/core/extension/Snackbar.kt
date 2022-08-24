package com.example.core.extension

import android.content.Context

import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.example.core.R
import com.google.android.material.snackbar.Snackbar


fun shortSnackbar(container: View, @StringRes textRes: Int, context: Context) {
    val  snack = Snackbar.make(container, textRes, Snackbar.LENGTH_SHORT)
    val view: View = snack.view
    val tv = view.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
    view.setBackgroundColor(ContextCompat.getColor(context, R.color.gray))
    tv.setBackgroundColor(ContextCompat.getColor(context, R.color.gray))
    snack.show()
}
