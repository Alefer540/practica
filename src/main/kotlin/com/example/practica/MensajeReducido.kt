package com.example.practica


class MensajeReducido { //le pasamos los mensajes y el id  evitando el idreferenciado para seguir con el formato correcto y elvitar idReferenciado:""
    var mensajes=""
    var id=0

    fun reducirMensaje(mensaje: Mensajes){
        id=mensaje.id
        mensajes=mensaje.mensaje
    }

}