package com.example.aplicacincliente.ui.chofer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacincliente.api.Restaurante
import com.example.aplicacincliente.repository.RestaurantRepository
import com.example.aplicacioncliente.R
import com.example.aplicacioncliente.ui.chofer.RestaurantAdapter
import com.example.aplicacioncliente.ui.chofer.RestaurantMenuActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var restaurantAdapter: RestaurantAdapter
    private val repository = RestaurantRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_list)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        Log.d("RestaurantListActivity", "Iniciando la actividad de lista de restaurantes")


        repository.getRestaurants().enqueue(object : Callback<List<Restaurante>> {
            override fun onResponse(call: Call<List<Restaurante>>, response: Response<List<Restaurante>>) {
                if (response.isSuccessful) {
                    Log.d("RestaurantListActivity", "Respuesta exitosa: " + response.body())
                    val restaurantList = response.body() ?: emptyList()
                    restaurantAdapter = RestaurantAdapter(restaurantList) { restaurant ->
                        val intent = Intent(this@RestaurantListActivity, RestaurantMenuActivity::class.java)
                        intent.putExtra("RESTAURANT_ID", restaurant.id.toString())
                        intent.putExtra("RESTAURANT_NAME", restaurant.nombre)
                        startActivity(intent)
                    }
                    recyclerView.adapter = restaurantAdapter
                } else {
                    Log.e("RestaurantListActivity", "Error en la respuesta: " + response.message())
                    Log.e("RestaurantListActivity", "Código de error: " + response.code())
                    Log.e("RestaurantListActivity", "Cuerpo del error: " + response.errorBody()?.string())
                    Toast.makeText(this@RestaurantListActivity, "Failed to fetch restaurants", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Restaurante>>, t: Throwable) {
                Log.e("RestaurantListActivity", "Fallo en la llamada: " + t.message)
                Toast.makeText(this@RestaurantListActivity, "Failed to fetch restaurants: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
