package com.example.practico_4_lista_de_contactos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddContactActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var companyEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var cityEditText: EditText
    private lateinit var stateEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var emailLabelEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var phoneLabelEditText: EditText
    private lateinit var profileImageView: ImageView
    private lateinit var selectImageButton: Button
    private lateinit var addContactButton: Button
    private var selectedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        nameEditText = findViewById(R.id.nameEditText)
        lastNameEditText = findViewById(R.id.lastNameEditText)
        companyEditText = findViewById(R.id.companyEditText)
        addressEditText = findViewById(R.id.addressEditText)
        cityEditText = findViewById(R.id.cityEditText)
        stateEditText = findViewById(R.id.stateEditText)
        emailEditText = findViewById(R.id.emailEditText)
        emailLabelEditText = findViewById(R.id.emailLabelEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        phoneLabelEditText = findViewById(R.id.phoneLabelEditText)
        profileImageView = findViewById(R.id.profileImageView)
        selectImageButton = findViewById(R.id.selectImageButton)
        addContactButton = findViewById(R.id.addContactButton)

        selectImageButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 100)
        }

        addContactButton.setOnClickListener {
            addContact()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.data
            profileImageView.setImageURI(selectedImageUri)
        }
    }

    private fun addContact() {
        val name = nameEditText.text.toString().trim()
        val lastName = lastNameEditText.text.toString().trim()
        val company = companyEditText.text.toString().trim()
        val address = addressEditText.text.toString().trim()
        val city = cityEditText.text.toString().trim()
        val state = stateEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val emailLabel = emailLabelEditText.text.toString().trim()
        val phone = phoneEditText.text.toString().trim()
        val phoneLabel = phoneLabelEditText.text.toString().trim()
        val profilePicture = selectedImageUri.toString()

        if (name.isEmpty() || lastName.isEmpty() || company.isEmpty() || address.isEmpty() || city.isEmpty() || state.isEmpty() || email.isEmpty() || emailLabel.isEmpty() || phone.isEmpty() || phoneLabel.isEmpty()) {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
            return
        }

        val newContact = Contacto(
            id = 0,
            name = name,
            last_name = lastName,
            company = company,
            address = address,
            city = city,
            state = state,
            telefonos = listOf(Telefono(id = 0, etiqueta = phoneLabel, numero = phone)),
            correos = listOf(Correo(id = 0, correo = email, etiqueta = emailLabel)),
            profile_picture = profilePicture
        )

        CoroutineScope(Dispatchers.IO).launch {
            try {
                RetrofitClient.instance.addContacto(newContact)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@AddContactActivity, "Contact added successfully", Toast.LENGTH_SHORT).show()
                    finish()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@AddContactActivity, "Failed to add contact", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
