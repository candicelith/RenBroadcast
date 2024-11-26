package com.example.renbroadcast

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.renbroadcast.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var prefManager: PrefManager
    //bikin akun dummy
    private var usernameAcc = "amba"
    private var passwordAcc = "123456"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefManager = PrefManager.getInstance(this)
        with(binding){
            btnLogin.setOnClickListener {
                val usernameInput = edtUsername.text.toString()
                val passwordInput = edtPassword.text.toString()

                if (usernameInput == usernameAcc
                    && passwordInput == passwordAcc){
                    prefManager.saveUsername(usernameInput)
                    checkLoginStatus()
                } else {
                    Toast.makeText(this@LoginActivity, "Username atau password salah",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun checkLoginStatus() {
        val username = prefManager.getUsername()
        if (username.isNotEmpty()){
            startActivity(
                Intent(this@LoginActivity, MainActivity::class.java)
            )
            finish()
        }
    }
}