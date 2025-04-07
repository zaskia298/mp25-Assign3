package com.example.tugas3.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tugas3.databinding.ActivityLoginBinding
import com.example.tugas3.model.User

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var registeredUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Terima data dari RegisterActivity
        registeredUser = User(
            name = intent.getStringExtra("REGISTERED_NAME") ?: "",
            email = intent.getStringExtra("REGISTERED_EMAIL") ?: "",
            password = intent.getStringExtra("REGISTERED_PASSWORD") ?: ""
        )

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (validateInput(email, password)) {
                if (email == registeredUser.email && password == registeredUser.password) {
                    // Kirim data ke HomeActivity
                    val intent = Intent(this, HomeActivity::class.java).apply {
                        putExtra("USER_NAME", registeredUser.name)
                    }
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Email atau password salah", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnGoToRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        var isValid = true

        binding.tilEmail.error = null
        binding.tilPassword.error = null

        if (email.isEmpty()) {
            binding.tilEmail.error = "Email tidak boleh kosong"
            isValid = false
        }

        if (password.isEmpty()) {
            binding.tilPassword.error = "Password tidak boleh kosong"
            isValid = false
        }

        return isValid
    }
}