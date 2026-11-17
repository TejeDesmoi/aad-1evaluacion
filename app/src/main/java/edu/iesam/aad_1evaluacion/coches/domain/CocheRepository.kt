package edu.iesam.aad_1evaluacion.coches.domain

interface CocheRepository {
    fun save(coche: Coche)
    fun delete(matricula: String)
    fun getById(matricula: String): Coche
    fun getAll(): List<Coche>
    fun update(coche: Coche)
}