package com.example.aplicacioncliente.ui.chofer

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacincliente.api.Restaurante
import com.example.aplicacioncliente.R

class MainActivity : AppCompatActivity() {

    private val locationPermissionRequestCode = 1
    private lateinit var recyclerView: RecyclerView
    private lateinit var restaurantAdapter: RestaurantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)


        val restaurantList = listOf(
            Restaurante(1, "Restaurant 1", "Address 1", 10.0, 20.0, 1),
            Restaurante(2, "Restaurant 2", "Address 2", 10.0, 20.0, 2),
            Restaurante(3, "Restaurant 3", "Address 3", 10.0, 20.0, 3)
        )
        restaurantAdapter = RestaurantAdapter(restaurantList) { restaurant ->

        }
        recyclerView.adapter = restaurantAdapter


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), locationPermissionRequestCode)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationPermissionRequestCode) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {

            } else {

            }
        }
    }
}
