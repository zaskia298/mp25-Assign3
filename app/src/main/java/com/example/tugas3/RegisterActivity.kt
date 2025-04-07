package com.example.tugas3.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tugas3.databinding.ActivityRegisterBinding
import com.example.tugas3.model.User

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (validateInput(name, email, password)) {
                // Kirim data ke LoginActivity
                val intent = Intent(this, LoginActivity::class.java).apply {
                    putExtra("REGISTERED_NAME", name)
                    putExtra("REGISTERED_EMAIL", email)
                    putExtra("REGISTERED_PASSWORD", password)
                }
                startActivity(intent)
                finish()

                Toast.makeText(this, "Registrasi berhasil! Silakan login", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnGoToLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun validateInput(name: String, email: String, password: String): Boolean {
        var isValid = true

        // Clear previous errors
        binding.tilName.error = null
        binding.tilEmail.error = null
        binding.tilPassword.error = null

        if (name.isEmpty()) {
            binding.tilName.error = "Nama tidak boleh kosong"
            isValid = false
        }

        if (email.isEmpty()) {
            binding.tilEmail.error = "Email tidak boleh kosong"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = "Format email tidak valid"
            isValid = false
        }

        if (password.isEmpty()) {
            binding.tilPassword.error = "Password tidak boleh kosong"
            isValid = false
        } else if (password.length < 6) {
            binding.tilPassword.error = "Password minimal 6 karakter"
            isValid = false
        }

        return isValid
    }
}