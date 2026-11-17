package edu.iesam.aad_1evaluacion.core.time

interface AppTime {
    fun getCurrentTime(): Long
}

class LocalTime: AppTime{
    override fun getCurrentTime(): Long {
        return System.currentTimeMillis()
    }

}