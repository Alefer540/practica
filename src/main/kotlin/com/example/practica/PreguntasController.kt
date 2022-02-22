package com.example.practica

import com.google.gson.Gson
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat
import java.util.*

@RestController

class PreguntasController(private val preguntasRepository: PreguntasRepository) {
    //curl --request POST  --header "Content-type:application/json; charset=utf-8" --data "pepe" localhost:8083/publicarTexto
    @PostMapping("publicarTexto")
    fun insertarmensaje(@RequestBody texto: String): Preguntas {
        val preg = Preguntas(texto)
        preguntasRepository.save(preg)
        println(preg)
        return preg
    }

    @GetMapping("show")
    fun mostrarUsuarios(): MutableList<Preguntas> {
        return preguntasRepository.findAll()
    }

    //curl --request GET --header "Content-type:application/json; charset=utf-8" --data "Mensa" localhost:8083/descargarFiltrado
    @GetMapping("descargarFiltrado")
    fun filtrado(@RequestBody texto: String): MutableList<Preguntas> {
        var listaMensajeFiltrado = mutableListOf<Preguntas>()
        preguntasRepository.findAll().forEach {
            if (it.mensaje.contains(texto)) {
                listaMensajeFiltrado.add(it)

            }

        }
        return listaMensajeFiltrado
    }

    @GetMapping("Borrar")
    fun delete(): Boolean {
        var borrado = false
        preguntasRepository.findAll().forEach {
            if (it.mensaje == " ") {
                borrado = true
                preguntasRepository.delete(it)
            } else {
                borrado = false
            }
        }
        return borrado
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


