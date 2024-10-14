package com.example.proyecto_3_tinder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PersonViewModel : ViewModel() {
    private val _people = MutableLiveData<List<Person>>(mutableListOf())
    val people: LiveData<List<Person>> get() = _people

    private val _likedPeople = MutableLiveData<List<Person>>(mutableListOf())
    val likedPeople: LiveData<List<Person>> get() = _likedPeople

    fun addPerson(person: Person) {
        val currentList = _people.value?.toMutableList() ?: mutableListOf()
        currentList.add(person)
        _people.value = currentList
    }

    fun likePerson(person: Person) {
        val currentLikedList = _likedPeople.value?.toMutableList() ?: mutableListOf()
        if (!currentLikedList.contains(person)) {
            currentLikedList.add(person)
            _likedPeople.value = currentLikedList
        }
        removePerson(person)
    }

    fun removePerson(person: Person) {
        val currentList = _people.value?.toMutableList() ?: mutableListOf()
        currentList.remove(person)
        _people.value = currentList
    }
}
