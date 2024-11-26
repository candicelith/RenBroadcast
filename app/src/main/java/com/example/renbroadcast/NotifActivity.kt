package com.example.renbroadcast

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.renbroadcast.databinding.ActivityNotifBinding

class NotifActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotifBinding
    private val channelId = "TEST_NOTIF"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNotifBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 100)
        }

        val notifManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        binding.btnNotif.setOnClickListener {

            val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.FLAG_IMMUTABLE
            }
            else {
                0
            }

            val logoutIntent = Intent(this, LogoutReceiver::class.java)
            val logoutPendingIntent = PendingIntent.getBroadcast(
                this, 0, logoutIntent, flag
            )

//            val intent = Intent(this@NotifActivity, NotifActivity::class.java)
////                .putExtra("MESSAGE", "Read more......")
//
//            val pendingIntent = PendingIntent.getActivity(
//                this,
//                0,
//                intent,
//                flag
//            )


            val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setContentTitle("My Notification")
                .setContentText("Ambatron Halo")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                .setContentIntent(pendingIntent)
                .addAction(0, "Logout Mas", logoutPendingIntent)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notifChannel = NotificationChannel(
                    channelId,
                    "My Notification",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                with(notifManager){
                    createNotificationChannel(notifChannel)
                    notify(0, builder.build())
                }
            } else {
                notifManager.notify(0, builder.build())
            }
        }
    }
}
