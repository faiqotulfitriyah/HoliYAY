package com.example.holiyay1.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.holiyay1.data.api.Outcome
import com.example.holiyay1.databinding.ActivitySignUpBinding
import com.example.holiyay1.ui.viewmodel.SignUpViewModel

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
//    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        signUpViewModel = ViewModelProvider(this)[SignUpViewModel::class.java]

        binding.signupButton.isEnabled = false

        binding.usernameEdittext.addTextChangedListener { validateInputs() }
        binding.passwordEdittext.addTextChangedListener { validateInputs() }

        binding.signupButton.setOnClickListener {
            binding.usernameEdittext.text.toString()
            binding.passwordEdittext.text.toString()

//            signUpUser(username, password)
            navigateToMainActivity()
        }

        binding.loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun validateInputs() {
        val username = binding.usernameEdittext.text.toString()
        val password = binding.passwordEdittext.text.toString()

        val isUsernameValid = username.isNotBlank()
        val isPasswordValid = password.isNotBlank()

        binding.signupButton.isEnabled = isUsernameValid && isPasswordValid
    }

//    private fun signUpUser(username: String, password: String) {
//        signUpViewModel.signUp(username, password).observe(this) {
//            if (it != null) {
//                when (it) {
//                    is Outcome.Loading -> {
//
//                    }
//                    is Outcome.Success -> {
//                        navigateToMainActivity()
//                    }
//                    is Outcome.Error -> {
//                        Toast.makeText(this, it.error, Toast.LENGTH_LONG).show()
//                    }
//                }
//            }
//        }
//    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
