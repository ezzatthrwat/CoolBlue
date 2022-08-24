package com.example.core.extension

import android.content.Context
import android.widget.Toast

fun showToast(context: Context, textString: String){
    Toast.makeText(context, textString, Toast.LENGTH_SHORT).show()
}