package edu.iesam.aad_1evaluacion

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import edu.iesam.aad_1evaluacion.coches.data.CocheDataRepository
import edu.iesam.aad_1evaluacion.coches.data.local.CocheXmlLocalDataSource
import edu.iesam.aad_1evaluacion.core.serialization.GsonJsonSerializer
import edu.iesam.aad_1evaluacion.coches.domain.Coche
import edu.iesam.aad_1evaluacion.core.Cache.CacheTTL
import edu.iesam.aad_1evaluacion.core.time.LocalTime

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val cocheDataRepository = CocheDataRepository(
            CocheXmlLocalDataSource(GsonJsonSerializer.create(),
                this,
                CacheTTL(this, LocalTime()))
        )

        cocheDataRepository.save(Coche("1","Ford","Mustang"))
        cocheDataRepository.save(Coche("2","Ford2","Mustang2"))
        cocheDataRepository.save(Coche("3","Ford3","Mustang3"))

        val coches = cocheDataRepository.getAll()
        Log.d("TEST", coches.toString())


        cocheDataRepository.save(Coche("4","Renault","Megan"))

        val coches2 = cocheDataRepository.getAll()
        Log.d("TEST", coches2.toString())

        val cocheId = cocheDataRepository.getById("4")
        Log.d("TEST", cocheId.toString())

        cocheDataRepository.delete("1")

        val coches3 = cocheDataRepository.getAll()
        Log.d("TEST", coches3.toString())

        cocheDataRepository.update(Coche("4","Renault","Coupe"))
        val coches4 = cocheDataRepository.getAll()
        Log.d("TEST", coches4.toString())
    }
}