package com.example.practica

import com.google.gson.Gson
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat
import java.util.*

@RestController

class PreguntasController(private val preguntasRepository: PreguntasRepository) {
    //curl --request POST  --header "Content-type:application/json; charset=utf-8" --data "pepe" localhost:8083/publicarTexto
    @PostMapping("publicarTexto")
    fun insertarmensaje(@RequestBody texto: String): Mensajes {
        val preg = Mensajes(texto)
        preguntasRepository.save(preg)
        println(preg)
        return preg
    }

    @GetMapping("show")
    fun mostrarUsuarios(): MutableList<Mensajes> {
        return preguntasRepository.findAll()
    }

    //curl --request GET --header "Content-type:application/json; charset=utf-8" --data "Mensa" localhost:8083/descargarFiltrado
    @GetMapping("descargarFiltrado")
    fun filtrado(@RequestBody texto: String): MensajesFiltrados {
        var filtrados = MensajesFiltrados()
        preguntasRepository.findAll().forEach {
            if (it.mensaje.contains(texto)) {
                filtrados.listaMensajesFiltrados.add(it)

            }

        }
        return filtrados
    }

    @GetMapping("Borrar")
    fun delete() {
        val listaMensajesBorrados = mutableListOf<Mensajes>()
        preguntasRepository.findAll().forEach {
            if (it.mensaje.equals(" ")) {
                listaMensajesBorrados.add(it)
            }
        }
        listaMensajesBorrados.forEach {
            preguntasRepository.delete(it)
        }
        preguntasRepository.findAll().forEach {
            println(it)
        }
    }
}


        //
        // EJERCICIO HECHO CON LOCALHOST
        /* @GetMapping("PublicarTexto/{mensajito}")
   // fun insertarMensaje(@PathVariable mensajito : String): Preguntas {
           // val preg= Preguntas(mensajito)
           // preguntasRepository.save(preg)
           // return preg
       // }
  }*/


