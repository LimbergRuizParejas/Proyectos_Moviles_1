package com.example.proyecto_3_tinder

import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    private val viewModel: PersonViewModel by viewModels()
    private var x1 = 0f
    private var x2 = 0f
    private val minDistance = 150

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace<PersonListFragment>(R.id.container)
            }
        }

        findViewById<Button>(R.id.liked_button).setOnClickListener {
            supportFragmentManager.commit {
                replace<LikedPeopleFragment>(R.id.container)
                addToBackStack(null)
            }
        }

        val imageView: ImageView = findViewById(R.id.image_view)
        val personNameTextView: TextView = findViewById(R.id.person_name_text_view)
        val likeButton: Button = findViewById(R.id.like_button)
        val dislikeButton: Button = findViewById(R.id.dislike_button)

        viewModel.people.observe(this, Observer { people ->
            if (people.isNotEmpty()) {
                val person = people[0]
                personNameTextView.text = person.name
                Glide.with(this)
                    .load(person.photos[0])
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.error_image)
                    .into(imageView)

                likeButton.setOnClickListener {
                    viewModel.likePerson(person)
                }

                dislikeButton.setOnClickListener {
                    viewModel.removePerson(person)
                }

                imageView.setOnTouchListener { _, event ->
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            x1 = event.x
                            true
                        }
                        MotionEvent.ACTION_UP -> {
                            x2 = event.x
                            val deltaX = x2 - x1
                            if (Math.abs(deltaX) > minDistance) {
                                if (x2 > x1) {
                                    viewModel.likePerson(person)
                                } else {
                                    viewModel.removePerson(person)
                                }
                                imageView.performClick()
                            }
                            true
                        }
                        else -> false
                    }
                }
                imageView.setOnClickListener { /* Manejo de clics */ }

            } else {
                personNameTextView.text = getString(R.string.no_more_people)
                imageView.setImageDrawable(null)
            }
        })

        // Añadir datos de prueba con imágenes locales
        viewModel.addPerson(Person("Rodrigo", listOf(
            R.drawable.imagen2,
            R.drawable.imagen2,
            R.drawable.imagen2
        )))

        viewModel.addPerson(Person("Mateo", listOf(
            R.drawable.persona1,
            R.drawable.persona1,
            R.drawable.persona1
        )))

        viewModel.addPerson(Person("Leo", listOf(
            R.drawable.persona2,
            R.drawable.persona2,
            R.drawable.persona2
        )))
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return super.onTouchEvent(event)
    }
}
