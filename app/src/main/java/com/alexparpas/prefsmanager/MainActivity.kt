package com.alexparpas.prefsmanager

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alexparpas.prefs.SharedPrefsManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SharedPrefsManager.init(app = application, defaultFileName = "prefs-sample")

        SharedPrefsManager.put(key = INT_KEY, value = 1) //Put Int in default prefs bucket
        SharedPrefsManager.put(key = STRING_KEY, value = "Hello world") //Put String in default prefs bucket
        SharedPrefsManager.put(key = STRING_KEY, value = "Hello world", fileName = "prefs-sample-bespoke") //Put string in bespoke prefs bucket
        SharedPrefsManager.put(key = OBJECT_KEY, value = ObjectSample("123", "Name")) //Put POJO

        SharedPrefsManager.getString(STRING_KEY) //Get String
        SharedPrefsManager.getInt(INT_KEY) //Get Int
        SharedPrefsManager.getObject(key = OBJECT_KEY, clazz = ObjectSample::class.java) //Get object

        SharedPrefsManager.remove(INT_KEY) //Remove Int value

        SharedPrefsManager.clear() //Clear preferences

    }

    companion object {
        const val INT_KEY = "int_key"
        const val STRING_KEY = "string_key"
        const val OBJECT_KEY = "pojo_key"
    }
}

data class ObjectSample(val id: String, val name: String)