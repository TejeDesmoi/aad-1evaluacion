package edu.iesam.aad_1evaluacion.core.Cache

import android.content.Context
import androidx.core.content.edit
import edu.iesam.aad_1evaluacion.R
import edu.iesam.aad_1evaluacion.core.time.AppTime

interface AppCache {
    fun updateCache(key: String)
    fun isExpired(key: String,ttl: Long): Boolean
}

class CacheTTL(context: Context,
               val appTime: AppTime): AppCache{
    val sharedPref = context.getSharedPreferences("cache_time_cars", Context.MODE_PRIVATE)

    override fun updateCache(key: String) {

        sharedPref.edit {
            putLong(key, appTime.getCurrentTime())
        }
    }

    override fun isExpired(key: String, ttl: Long): Boolean {
        val cache = sharedPref.getLong(key, 0)

        return cache > (appTime.getCurrentTime()-ttl)
    }

}