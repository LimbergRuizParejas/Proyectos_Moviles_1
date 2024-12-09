package com.example.aplicacioncliente.ui.chofer

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacioncliente.R
import com.example.aplicacioncliente.repository.ProductRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class RestaurantMenuActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private val repository = ProductRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_menu)

        val token = "tu_token"

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)


        lifecycleScope.launch {
            try {
                val response = repository.getProducts(token)
                if (response.isSuccessful) {
                    val productList = response.body() ?: emptyList()
                    productAdapter = ProductAdapter(productList)
                    recyclerView.adapter = productAdapter
                } else {
                    Toast.makeText(this@RestaurantMenuActivity, "Failed to fetch products", Toast.LENGTH_SHORT).show()
                }
            } catch (e: IOException) {
                Toast.makeText(this@RestaurantMenuActivity, "Network error, please try again.", Toast.LENGTH_SHORT).show()
            } catch (e: HttpException) {
                Toast.makeText(this@RestaurantMenuActivity, "Server error, please try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
