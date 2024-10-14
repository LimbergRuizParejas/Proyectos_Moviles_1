package com.example.proyecto_3_tinder

import androidx.annotation.DrawableRes

data class Person(
    val name: String,
    @DrawableRes val photos: List<Int>
)
