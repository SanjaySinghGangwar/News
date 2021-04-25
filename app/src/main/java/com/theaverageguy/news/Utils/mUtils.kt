package com.theaverageguy.news.Utils

import android.content.Context
import android.util.Log
import android.widget.Toast

object mUtils {
    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun log(message: String) {
        Log.i("SANJAY ", "---------------------------------------")
        Log.i("SANJAY ", "log: ------> $message")
        Log.i("SANJAY ", "---------------------------------------")
    }
}