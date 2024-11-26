package com.example.renbroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class LogoutReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            PrefManager.getInstance(it).clear()

            val loginIntent = Intent(it, LoginActivity::class.java)
            loginIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            it.startActivity(loginIntent)
        }
    }
}