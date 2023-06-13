package com.example.holiyay1.data.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.example.holiyay1.R
import com.example.holiyay1.data.ui.CustomEditText

class SignUpActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: CustomEditText
    private lateinit var signupButton: Button
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        nameEditText = findViewById(R.id.name_edittext)
        emailEditText = findViewById(R.id.email_edittext)
        passwordEditText = findViewById(R.id.password_edittext)
        signupButton = findViewById(R.id.signup_button)
        loginButton = findViewById(R.id.login_button)

        signupButton.isEnabled = false // Disable login button initially

        emailEditText.addTextChangedListener { validateInputs() }
        passwordEditText.addTextChangedListener { validateInputs() }

        signupButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Skip API call and directly navigate to MainActivity
            navigateToMainActivity()
        }

        loginButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun validateInputs() {
        val name = nameEditText.text.toString()
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        val isNameValid = name.isNotBlank()
        val isEmailValid = email.isNotBlank()
        val isPasswordValid = password.isNotBlank()

        signupButton.isEnabled = isNameValid && isEmailValid && isPasswordValid
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Optional: Close LoginActivity to prevent going back to it via back button
    }
}