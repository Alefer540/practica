package com.example.practica


import com.google.gson.Gson
import javax.persistence.ElementCollection
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
@Entity
class Respuestas(var idpreg:Int){
 @ElementCollection
 var respues= mutableListOf<String>()
 @Id
 var id=this.idpreg
 override fun toString():String{
  val gson= Gson()
  return gson.toJson(this)
 }
}




