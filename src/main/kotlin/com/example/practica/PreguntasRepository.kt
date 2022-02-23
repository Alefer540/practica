package com.example.practica

import org.springframework.data.jpa.repository.JpaRepository

interface PreguntasRepository : JpaRepository<Mensajes, Int>
