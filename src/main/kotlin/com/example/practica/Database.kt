package com.example.practica

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Database {
    @Bean
    fun initDatabase(preguntasRepository: PreguntasRepository,respuestasRepository: RespuestasRepository): CommandLineRunner {
        return CommandLineRunner {
        }
    }
}