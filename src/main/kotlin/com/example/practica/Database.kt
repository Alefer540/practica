package com.example.practica

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Database {
    @Bean
    fun initDatabase(mensajesRepository: MensajesRepository): CommandLineRunner {//le pasamos la clase MensajesRepository ya que vamos a trabajar con ella
        return CommandLineRunner {
        }
    }
}