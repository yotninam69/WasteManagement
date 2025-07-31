package com.example.angeleswestmanagement

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)



        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // UI Elements
        val emailEditText = findViewById<TextInputEditText>(R.id.emailEditText)
        val passwordEditText = findViewById<TextInputEditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val signupPromptText = findViewById<TextView>(R.id.signupPromptText)
        val forgotPasswordText = findViewById<TextView>(R.id.forgotPasswordText)

        // Login Button Click
        loginButton.setOnClickListener {
            val email = emailEditText.text?.toString()?.trim()
            val password = passwordEditText.text?.toString()

            if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                        // TODO: Navigate to your Main/Dashboard Activity
                        // startActivity(Intent(this, MainActivity::class.java))
                        // finish()
                    } else {
                        Toast.makeText(this, "Login failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }
        }

        // Navigate to Sign Up
        signupPromptText.setOnClickListener {
            startActivity(Intent(this, Signup::class.java))
        }

        // Forgot Password Placeholder
        forgotPasswordText.setOnClickListener {
            Toast.makeText(this, "Forgot password clicked", Toast.LENGTH_SHORT).show()
            // TODO: Implement password reset logic
        }
    }
}
