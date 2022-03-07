package com.example.practica

import com.google.gson.Gson
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.system.exitProcess

@RestController

class PreguntasController(private val mensajesRepository: MensajesRepository) {//le pasamos el mensaje repository

    @GetMapping("show")
    fun mostrarUsuarios(): MutableList<Mensajes> {
        return mensajesRepository.findAll()
    }

    //curl --request POST  --header "Content-type:application/json; charset=utf-8" --data "pepe" localhost:8083/publicarTexto
    @PostMapping("publicarTexto")
    fun insertarmensaje(@RequestBody texto: String): MensajeReducido {
        val mensaje = Mensajes(texto)
        mensajesRepository.save(mensaje)
        val mensajereducido=MensajeReducido()
        mensajereducido.reducirMensaje(mensaje)
        mensajesRepository.save(mensaje)
        return mensajereducido
    }

    //curl --request GET --header "Content-type:application/json; charset=utf-8" --data "Mensa" localhost:8083/descargarFiltrado
    @GetMapping("descargarFiltrado")
    fun filtrado(@RequestBody texto: String): Any {//Any devuelve cualquier cosa
        val lista = ListaDeMensajes(mensajesRepository.findAll().filter { it.mensaje.contains(texto) })//creamos una variable lista de la clase ListaDeMensajes
        // para que funcione le pasamos por parametro una lista findAll coge los mensajes que contienen texto
        val filtrados = ListaMensajesReducidos()//le damos el formato sin id referenciado
        filtrados.transformar(lista)//transformamos lista que contiene all
        if (filtrados.listaDeMensajesFiltrados.isEmpty())
            return "ERROR NOT FOUND"
        else
            return filtrados
    }

    @GetMapping("Borrar")
    fun delete() {
        mensajesRepository.deleteAll(mensajesRepository.findAll().filter { it.mensaje.isBlank() || it.mensaje.isEmpty() })
    }
    @GetMapping("primeros10Mensajes")
    fun mostar():Any{
        val lista=ListaDeMensajes(mensajesRepository.findAll().filter { it.id<=10 })
        val lista10primeros=ListaMensajesReducidos()
        lista10primeros.transformar(lista)
            return lista10primeros
    }

    @GetMapping("ultimos10Mensajes")
    fun mostar2():ListaDeMensajes{
        val lista10ultimos=ListaDeMensajes(mensajesRepository.findAll())
        return if(lista10ultimos.listaMensajes.size < 10){
         lista10ultimos
        }else{
             ListaDeMensajes(mensajesRepository.findAll().filter { it.id > (lista10ultimos.listaMensajes.size -10)})
    }
    }

    @GetMapping("idComprendidos/{id1}/{id2}")
    fun comprendidos(@PathVariable id1:Int,@PathVariable id2:Int):ListaMensajesReducidos {
        val lista=ListaDeMensajes(mensajesRepository.findAll().filter{ it.id in id1..id2})
        val mensajesComprendidos=ListaMensajesReducidos()
        mensajesComprendidos.transformar(lista)
        return mensajesComprendidos
    }

    @GetMapping("responderMensajes/{id}/{respuesta}")
    fun respondermensaje(@PathVariable id:Int,@PathVariable respuesta:String){
        val mensajes=Mensajes(respuesta, idRenfereciado = id)
        mensajesRepository.save(mensajes)
    }
    @GetMapping("obtenerMensajesSoloRespondiendo/{id}")
    fun obtenermensajes(@PathVariable id:Int):ListaDeMensajes{
        val lista =ListaDeMensajes(mensajesRepository.findAll().filter {it.idRenfereciado == id  })
        return lista
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


