package com.example.practica

import com.google.gson.Gson
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.system.exitProcess

@RestController

class PreguntasController(private val preguntasRepository: PreguntasRepository,private val respuestasRepository: RespuestasRepository) {
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
    fun filtrado(@RequestBody texto: String): Any {
        val filtrados = MensajesFiltrados()
        preguntasRepository.findAll().forEach {
            if (it.mensaje.contains(texto)) {
                filtrados.listaMensajesFiltrados.add(it)
            }
        }
        if(filtrados.listaMensajesFiltrados.isEmpty()){
            return "ERROR NOT FOUND"
        }
        return filtrados
    }

    @GetMapping("Borrar")
    fun delete() {
        val listaMensajesBorrados = mutableListOf<Mensajes>()
        preguntasRepository.findAll().forEach {
            if (it.mensaje.isEmpty() || it.mensaje.isBlank()) {
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
    fun mostar():MensajesFiltrados{
        val primeros10=MensajesFiltrados()
        preguntasRepository.findAll().forEach {
            if(it.id<=5){
                primeros10.listaMensajesFiltrados.add(it)
        }

    }
    return primeros10
}

    @GetMapping("ultimos10Mensajes")
    fun mostar2():MensajesFiltrados{
        var mens=MensajesFiltrados()
        var cont = 0
        preguntasRepository.findAll().asReversed().forEach {
            if (cont < 5){
                mens.listaMensajesFiltrados.add(it)
            }
            cont++
        }
        return mens
    }
    @GetMapping("idCompendidos/{id1}/{id2}")
    fun comprendidos(@PathVariable id1:Int,@PathVariable id2:Int):MensajesFiltrados {
        var mensajesComprendidos=MensajesFiltrados()
        preguntasRepository.findAll().forEach {
            if(it.id in id1..id2){
                mensajesComprendidos.listaMensajesFiltrados.add(it)
            }
        }
        return mensajesComprendidos
    }
    @GetMapping("chat/{idx}/{respuesta}")
    fun chat(@PathVariable idx:Int,@PathVariable respuesta:String){

        respuestasRepository.findAll().forEach {
            if(it.id==idx){
                it.respues.add(respuesta)
            }else{
                var respuestanueva=Respuestas(idx)
                respuestanueva.respues.add(respuesta)
                respuestasRepository.save(respuestanueva)
            }
        }
    }
    @GetMapping("damerespuestaas/{idx}")
    fun dame(@PathVariable idx:Int):Any{
        var flag=false
        var posiblerespuesta=Respuestas(idx)

        respuestasRepository.findAll().forEach {
           if(it.id==idx){
              return it
           }else{
           flag=true
           }
        }
        if(flag){
            return "No existe repuesta con este id"
        }else return posiblerespuesta


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


