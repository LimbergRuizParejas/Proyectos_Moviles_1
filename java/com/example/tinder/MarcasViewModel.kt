package com.example.tinder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MarcasViewModel : ViewModel() {
    private val _listaMarcas = MutableLiveData<List<Marca>>()
    val listaMarcas: LiveData<List<Marca>> = _listaMarcas

    private val _marcaActual = MutableLiveData<Marca>()
    val marcaActual: LiveData<Marca> = _marcaActual

    private val _misLikes = MutableLiveData<List<Marca>>()
    val misLikes: LiveData<List<Marca>> = _misLikes

    init {
        _listaMarcas.value = listOf(
            Marca(
                nombre = "PIL",
                fotos = listOf(
                    "android.resource://com.example.tinder/drawable/pillogo",
                    "android.resource://com.example.tinder/drawable/lechepil",
                    "android.resource://com.example.tinder/drawable/pilfrut",
                    "android.resource://com.example.tinder/drawable/chiquichoc"
                ),
            ),
            Marca(
                nombre = "Coca-Cola",
                fotos = listOf(
                    "android.resource://com.example.tinder/drawable/cocacola",
                    "android.resource://com.example.tinder/drawable/cocacola2",
                    "android.resource://com.example.tinder/drawable/fanta",
                    "android.resource://com.example.tinder/drawable/papaya"
                ),
            ),
            Marca(
                nombre = "Pilfrut",
                fotos = listOf(
                    "android.resource://com.example.tinder/drawable/pilfrutlogo",
                    "android.resource://com.example.tinder/drawable/pilfrutchico",
                    "android.resource://com.example.tinder/drawable/pilfrutmanzana"
                ),
            ),
            Marca(
                nombre = "Doña Gusta",
                fotos = listOf(
                    "android.resource://com.example.tinder/drawable/donagusta",
                    "android.resource://com.example.tinder/drawable/",
                    "android.resource://com.example.tinder/drawable/",
                    "android.resource://com.example.tinder/drawable/"
                ),
            ),
            Marca(
                nombre = "OMO",
                fotos = listOf(
                    "android.resource://com.example.tinder/drawable/omo",
                    "android.resource://com.example.tinder/drawable/omooriginal",
                    "android.resource://com.example.tinder/drawable/omodetergente",
                    "android.resource://com.example.tinder/drawable/omobicarbonato"
                ),
            ),
            Marca(
                nombre = "Scott",
                fotos = listOf(
                    "android.resource://com.example.tinder/drawable/scotlogo",
                    "android.resource://com.example.tinder/drawable/scot_aroma_completo",
                    "android.resource://com.example.tinder/drawable/scot_cuidadoclasico",
                    "android.resource://com.example.tinder/drawable/scott_aromas"
                ),
            ),
            Marca(
                nombre = "Pepsi",
                fotos = listOf(
                    "android.resource://com.example.tinder/drawable/pepsi",
                    "android.resource://com.example.tinder/drawable/pepsinormal2",
                    "android.resource://com.example.tinder/drawable/pepsiblack",
                    "android.resource://com.example.tinder/drawable/pesinormal"
                ),
            ),
            Marca(
                nombre = "Mabel’s",
                fotos = listOf(
                    "android.resource://com.example.tinder/drawable/mabelslogo",
                    "android.resource://com.example.tinder/drawable/mabelsrosquitas",
                    "android.resource://com.example.tinder/drawable/pepsiblack",
                    "android.resource://com.example.tinder/drawable/pesinormal"
                ),
            ),
            Marca(
                nombre = "Sedal",
                fotos = listOf(
                    "android.resource://com.example.tinder/drawable/sedal",
                    "android.resource://com.example.tinder/drawable/sedalceramidas",
                    "android.resource://com.example.tinder/drawable/sedalrestau",
                    "android.resource://com.example.tinder/drawable/sedalrizos"
                ),
            ),
            Marca(
                nombre = "OLA",
                fotos = listOf(
                    "android.resource://com.example.tinder/drawable/ola",
                    "android.resource://com.example.tinder/drawable/olacuidado",
                    "android.resource://com.example.tinder/drawable/olalimpieza",
                    "android.resource://com.example.tinder/drawable/ola_detergente"
                ),
            ),
            Marca(
                nombre = "La Suprema",
                fotos = listOf(
                    "android.resource://com.example.tinder/drawable/lasuprema",
                    "android.resource://com.example.tinder/drawable/galletasuprema",
                    "android.resource://com.example.tinder/drawable/galletasuprema2",
                    "android.resource://com.example.tinder/drawable/galletacrkackers"
                ),
            ),
            Marca(
                nombre = "Kris",
                fotos = listOf(
                    "android.resource://com.example.tinder/drawable/kris",
                    "android.resource://com.example.tinder/drawable/kechutpkrius",
                    "android.resource://com.example.tinder/drawable/mostazakris",
                    "android.resource://com.example.tinder/drawable/kechutpkrius"
                ),
            ),
            Marca(
                nombre = "Lazzaroni",
                fotos = listOf(
                    "android.resource://com.example.tinder/drawable/lazzaroni",
                    "android.resource://com.example.tinder/drawable/lazzaroni1",
                    "android.resource://com.example.tinder/drawable/lazzaroni2",
                    "android.resource://com.example.tinder/drawable/lazzaroni3"
                ),
            ),
            Marca(
                nombre = "Famosa",
                fotos = listOf(
                    "android.resource://com.example.tinder/drawable/famosa",
                    "android.resource://com.example.tinder/drawable/famosa1",
                    "android.resource://com.example.tinder/drawable/famosa2",
                    "android.resource://com.example.tinder/drawable/famosa3"
                ),
            ),
            Marca(
                nombre = "UNO",
                fotos = listOf(
                    "android.resource://com.example.tinder/drawable/uno",
                    "android.resource://com.example.tinder/drawable/uno1",
                    "android.resource://com.example.tinder/drawable/uno2",
                    "android.resource://com.example.tinder/drawable/uno3"
                ),
            )
        )
        _marcaActual.value = _listaMarcas.value?.firstOrNull()
    }

    fun likeActual() {
        val likes = _misLikes.value?.toMutableList() ?: mutableListOf()
        _marcaActual.value?.let { likes.add(it) }
        _misLikes.value = likes
        removeCurrentMarca()
    }

    fun dislikeActual() {
        removeCurrentMarca()
    }

    private fun removeCurrentMarca() {
        val currentMarca = _marcaActual.value ?: return
        val updatedList = _listaMarcas.value?.toMutableList() ?: return
        updatedList.remove(currentMarca)
        if(updatedList.isEmpty()){
            _marcaActual.value = Marca(nombre = "Ya No Hay Mas Marcas", fotos = listOf("nourl"))
        }else{
            _listaMarcas.value = updatedList
        }
        if (updatedList.isEmpty()) {
        } else {
            _marcaActual.value = updatedList.firstOrNull()
        }
    }
}
