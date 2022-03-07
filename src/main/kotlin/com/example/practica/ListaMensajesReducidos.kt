package com.example.practica

class ListaMensajesReducidos {

    var listaDeMensajesFiltrados = mutableListOf<MensajeReducido>()

    fun transformar(lista : ListaDeMensajes){//va a transformar una lista de la clase mensajes  y esta mensajes reducidos
        lista.listaMensajes.forEach(){
            val mensajeReducido=MensajeReducido()
            mensajeReducido.reducirMensaje(it)
           listaDeMensajesFiltrados.add(mensajeReducido)
        }
    }
}