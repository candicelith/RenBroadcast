package com.example.renbroadcast

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.renbroadcast.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var prefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //instance pref manager
        prefManager = PrefManager.getInstance(this)
        checkLoginStatus()
        with(binding){
            txtUsername.text = prefManager.getUsername()
            btnLogout.setOnClickListener {
                prefManager.saveUsername("") //ngeset kosong
                checkLoginStatus()
            }
            btnClear.setOnClickListener {
                prefManager.clear() //menghapus semua data
                checkLoginStatus()
            }
        }
    }

    private fun checkLoginStatus() {
        if (prefManager.getUsername().isEmpty()){
            startActivity(
                Intent(this@MainActivity, LoginActivity::class.java)
            )
            finish()
        }
    }
}