package com.example.aplicacioncliente.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicacioncliente.R
import com.example.aplicacincliente.api.ApiClient
import com.example.aplicacioncliente.api.AuthApi
import com.example.aplicacioncliente.api.LoginResponse
import com.example.aplicacioncliente.repository.UserRepository
import com.example.aplicacincliente.ui.chofer.RestaurantListActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button

    private val authApi = ApiClient.retrofit.create(AuthApi::class.java)
    private val userRepository = UserRepository(authApi)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        registerButton = findViewById(R.id.registerButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            } else {
                loginUser(email, password)
            }
        }

        registerButton.setOnClickListener {

            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response: Response<LoginResponse> = userRepository.login(email, password)
                runOnUiThread {
                    if (response.isSuccessful) {
                        Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this@LoginActivity, RestaurantListActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@LoginActivity, "Login Failed: ${response.errorBody()?.string()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this@LoginActivity, "Login Failed: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
