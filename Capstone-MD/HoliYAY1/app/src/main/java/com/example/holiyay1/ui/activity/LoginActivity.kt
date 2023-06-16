package com.example.holiyay1.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import androidx.core.widget.addTextChangedListener
import com.example.holiyay1.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.isEnabled = false

        binding.userNameEdittext.addTextChangedListener { validateInputs() }
        binding.passwordEdittext.addTextChangedListener { validateInputs() }

        binding.loginButton.setOnClickListener {
            binding.userNameEdittext.text.toString()
            binding.passwordEdittext.text.toString()

            navigateToMainActivity()
        }

        binding.signupButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun validateInputs() {
        val username = binding.userNameEdittext.text.toString()
        val password = binding.passwordEdittext.text.toString()

        val isUsernameValid = username.isNotBlank()
        val isPasswordValid = password.isNotBlank()

        binding.loginButton.isEnabled = isUsernameValid && isPasswordValid
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
