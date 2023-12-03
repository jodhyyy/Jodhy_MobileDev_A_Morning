package com.example.jodhymobiledevmorning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jodhymobiledevmorning.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val user = User(binding.etUsername.text.toString(), binding.etPassword.text.toString())
            val intent = Intent(this, Dashboard::class.java)
            intent.putExtra("User", user)
            startActivity(intent)
        }
    }
}
