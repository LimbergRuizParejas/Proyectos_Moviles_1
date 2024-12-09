package com.example.aplicacioncliente.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicacincliente.api.ApiClient
import com.example.aplicacioncliente.api.AuthApi
import com.example.aplicacioncliente.R
import com.example.aplicacioncliente.api.RegisterResponse
import com.example.aplicacioncliente.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button

    private val authApi = ApiClient.retrofit.create(AuthApi::class.java)
    private val userRepository = UserRepository(authApi)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        nameEditText = findViewById(R.id.nameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        registerButton = findViewById(R.id.registerButton)

        registerButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val role = 1
            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            } else {
                registerUser(name, email, password, role)
            }
        }
    }

    private fun registerUser(name: String, email: String, password: String, role: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response: Response<RegisterResponse> = userRepository.register(email, password, name, role)
                runOnUiThread {
                    if (response.isSuccessful) {
                        Toast.makeText(this@RegisterActivity, "Registration Successful", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@RegisterActivity, "Registration Failed: ${response.errorBody()?.string()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this@RegisterActivity, "Registration Failed: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
