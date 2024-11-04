package com.example.practico_4_lista_de_contactos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var contactosAdapter: ContactosAdapter
    private lateinit var addButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        contactosAdapter = ContactosAdapter(emptyList())
        recyclerView.adapter = contactosAdapter

        addButton = findViewById(R.id.addButton)
        addButton.setOnClickListener {
            val intent = Intent(this, AddContactActivity::class.java)
            startActivity(intent)
        }

        fetchContactos()
    }

    override fun onResume() {
        super.onResume()
        fetchContactos()
    }

    private fun fetchContactos() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val contactos = RetrofitClient.instance.getContactos()
                withContext(Dispatchers.Main) {
                    if (contactos.isNotEmpty()) {
                        contactosAdapter.updateContactos(contactos)
                    } else {
                        // Manejo de la situación cuando no hay contactos
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Manejo del error, por ejemplo, mostrar un mensaje al usuario
            }
        }
    }
}
