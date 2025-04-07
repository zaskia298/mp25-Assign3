package com.example.tugas3.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tugas3.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName = intent.getStringExtra("USER_NAME") ?: "Pengguna"
        binding.tvWelcome.text = "Selamat datang, $userName!"
    }
}