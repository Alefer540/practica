package com.example.practica


import com.google.gson.Gson
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
@Entity
 data class Mensajes(var mensaje:String,) {
  @Id
  @GeneratedValue
  var id=0

 override fun toString():String{
  val gson= Gson()
  return gson.toJson(this)
 }
}
class MensajesFiltrados() {
 val listaMensajesFiltrados = mutableListOf<Mensajes>()
 override fun toString(): String {
  val gson = Gson()
  return gson.toJson(this)

 }
}
 class Primeros10() {
  var primeros10Mensajes = ArrayList<Mensajes>()
  override fun toString(): String {
   val gson = Gson()
   return gson.toJson(this)

  }
}