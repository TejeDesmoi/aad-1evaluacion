package edu.iesam.aad_1evaluacion.coches.data

import edu.iesam.aad_1evaluacion.coches.data.local.CocheXmlLocalDataSource
import edu.iesam.aad_1evaluacion.coches.domain.Coche
import edu.iesam.aad_1evaluacion.coches.domain.CocheRepository

class CocheDataRepository(
    val cocheXmlLocalDataSource: CocheXmlLocalDataSource
): CocheRepository {
    override fun save(coche: Coche) {
        cocheXmlLocalDataSource.save(coche)
    }

    override fun delete(matricula: String) {
        cocheXmlLocalDataSource.delete(matricula)
    }

    override fun getById(matricula: String): Coche {
        return cocheXmlLocalDataSource.getById(matricula)
    }

    override fun getAll(): List<Coche> {
        return cocheXmlLocalDataSource.getAll()
    }

    override fun update(coche: Coche) {
        return cocheXmlLocalDataSource.update(coche)
    }
}