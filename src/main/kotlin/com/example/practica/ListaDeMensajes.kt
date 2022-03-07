package com.example.practica

import com.google.gson.Gson

class ListaDeMensajes( val listaMensajes : List<Mensajes>){//a la clase le llega como parametro un objeto de la clase list que pertenece a kotlin
    override fun toString(): String {
        val gson = Gson()
        return gson.toJson(this)

    }
}