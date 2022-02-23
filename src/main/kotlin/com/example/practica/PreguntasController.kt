package com.example.practica

import com.google.gson.Gson
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.system.exitProcess

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
    fun filtrado(@RequestBody texto: String): String {
        var filtrados = MensajesFiltrados()
        preguntasRepository.findAll().forEach {
            if (it.mensaje.contains(texto)) {
                filtrados.listaMensajesFiltrados.add(it)
            }else {
                return "ERROR NOT FOUND"
            }

        }
        return filtrados.toString()
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
    @GetMapping("primeros10Mensajes")
    fun mostar():Primeros10{
        val primeros10=Primeros10()
        preguntasRepository.findAll().forEach {
            if(it.id<=5){
                primeros10.primeros10Mensajes.add(it)
        }

    }
    return primeros10
}

    @GetMapping("ultimos10Mensajes")
    fun mostar2():Primeros10{
        var mens=Primeros10()
        var cont = 0
        preguntasRepository.findAll().asReversed().forEach {
            if (cont < 5){
                mens.primeros10Mensajes.add(it)
            }
            cont++
        }
        return mens
    }

}

if (it.id in num1..num2){
    lista.add(it)
}
return lista
        //
        // EJERCICIO HECHO CON LOCALHOST
        /* @GetMapping("PublicarTexto/{mensajito}")
   // fun insertarMensaje(@PathVariable mensajito : String): Preguntas {
           // val preg= Preguntas(mensajito)
           // preguntasRepository.save(preg)
           // return preg
       // }
  }*/


