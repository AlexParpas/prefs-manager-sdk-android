package com.alexparpas.prefsmanager

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alexparpas.prefs.PrefsManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PrefsManager.init(app = application, defaultFileName = "prefs-sample")

        PrefsManager.put(key = INT_KEY, value = 1) //Put Int in default prefs bucket
        PrefsManager.put(key = STRING_KEY, value = "Hello world") //Put String in default prefs bucket
        PrefsManager.put(key = STRING_KEY, value = "Hello world", fileName = "prefs-sample-bespoke") //Put string in bespoke prefs bucket
        PrefsManager.put(key = POJO_KEY, value = POJOSample("123", "Name")) //Put POJO

        PrefsManager.getString(STRING_KEY) //Get String
        PrefsManager.getInt(INT_KEY) //Get Int
        PrefsManager.getPOJO<POJOSample>(key = POJO_KEY) //Get POJO

        PrefsManager.remove(INT_KEY) //Remove Int value

        PrefsManager.clear() //Clear preferences

    }

    companion object {
        const val INT_KEY = "int_key"
        const val STRING_KEY = "string_key"
        const val POJO_KEY = "pojo_key"
    }
}

data class POJOSample(val id: String, val name: String)