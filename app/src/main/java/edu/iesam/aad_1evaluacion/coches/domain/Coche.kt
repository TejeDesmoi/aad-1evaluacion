package edu.iesam.aad_1evaluacion.coches.domain

import kotlinx.serialization.Serializable

@Serializable
data class Coche (
    val matricula: String,
    val marca: String,
    val modelo: String
)