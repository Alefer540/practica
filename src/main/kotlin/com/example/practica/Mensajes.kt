package com.example.practica


import com.google.gson.Gson
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
@Entity//cuando marcamos algo con entity podemos grabar objetos en repositorio
 data class Mensajes(var mensaje:String,var idRenfereciado:Int? = null) {
  @Id
  @GeneratedValue
  var id=0

 override fun toString():String{
  val gson= Gson()
  return gson.toJson(this)
 }
}






