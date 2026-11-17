package edu.iesam.aad_1evaluacion.coches.data.local

import android.content.Context
import androidx.core.content.edit
import edu.iesam.aad_1evaluacion.R
import edu.iesam.aad_1evaluacion.coches.domain.Coche
import edu.iesam.aad_1evaluacion.core.serialization.JsonSerializer

class CocheXmlLocalDataSource(
    val serializer: JsonSerializer<List<Coche>>,
    val context: Context
) {

    val sharedPref = context.getSharedPreferences(
        context.getString(R.string.xml_local_source), Context.MODE_PRIVATE
    )

    fun getAll(): List<Coche> {

        val coches =  sharedPref.getString(carKey, null)

        coches?.let {
            return serializer.fromJson(coches)
        }

        return emptyList()
    }

    fun getById(matricula: String): Coche{
        val coches = getAll().toMutableList()

        coches.let {
            return coches.first { it.matricula == matricula }
        }
    }

    fun save(coche: Coche){
        val coches = getAll().toMutableList()
        val coches2 = coches.add(coche)

        coches.let {
            sharedPref.edit {
                putString(carKey, serializer.toJson(coches))
                apply()
            }
        }
    }

    fun delete(matricula: String){
        val coches = getAll()
        val coches2 = coches.filterNot { it.matricula == matricula }

        sharedPref.edit{
            putString(carKey, serializer.toJson(coches2))
            apply()
        }
    }

    fun update(coche: Coche){
        val coches = getAll()
        val coches2 = coches.filterNot { it.matricula == coche.matricula }.toMutableList()

        coches2.add(coche)

        sharedPref.edit{
            putString(carKey, serializer.toJson(coches2))
            apply()
        }
    }

    companion object{
        val carKey = "Cars"
    }

}