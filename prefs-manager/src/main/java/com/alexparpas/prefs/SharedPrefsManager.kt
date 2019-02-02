package com.alexparpas.prefs

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

object SharedPrefsManager : PrefsManager {
    private lateinit var app: Application
    override lateinit var defaultFileName: String
    private var initialised = false

    override fun init(app: Application, defaultFileName: String) {
        this.app = app
        this.defaultFileName = defaultFileName
        initialised = true
    }

    override fun getString(key: String, defaultValue: String?, fileName: String): String? {
        return getPrefs(fileName).getString(key, defaultValue)
    }

    override fun getSet(key: String, defaultValue: Set<String>?, fileName: String): Set<String>? {
        return getPrefs(fileName).getStringSet(key, defaultValue)
    }

    override fun getInt(key: String, defaultValue: Int, fileName: String): Int? {
        return getPrefs(fileName).getInt(key, defaultValue)
    }

    override fun getLong(key: String, defaultValue: Long, fileName: String): Long? {
        return getPrefs(fileName).getLong(key, defaultValue)
    }

    override fun getFloat(key: String, defaultValue: Float, fileName: String): Float? {
        return getPrefs(fileName).getFloat(key, defaultValue)
    }

    override fun getBoolean(key: String, defaultValue: Boolean, fileName: String): Boolean? {
        return getPrefs(fileName).getBoolean(key, defaultValue)
    }

    override fun <T> getObject(key: String, clazz: Class<T>, defaultValue: T?, fileName: String): T? {
        val rawValue = getPrefs(fileName).getString(key, null)

        return if (rawValue != null) {
            Gson().fromJson<T>(rawValue, clazz)
        } else {
            defaultValue
        }
    }

    override fun put(key: String, value: String, fileName: String) {
        getPrefsEditor(fileName).putString(key, value).apply()
    }

    override fun put(key: String, value: Set<String>, fileName: String) {
        getPrefsEditor(fileName).putStringSet(key, value).apply()
    }

    override fun put(key: String, value: Int, fileName: String) {
        getPrefsEditor(fileName).putInt(key, value).apply()
    }

    override fun put(key: String, value: Long, fileName: String) {
        getPrefsEditor(fileName).putLong(key, value).apply()
    }

    override fun put(key: String, value: Float, fileName: String) {
        getPrefsEditor(fileName).putFloat(key, value).apply()
    }

    override fun put(key: String, value: Boolean, fileName: String) {
        getPrefsEditor(fileName).putBoolean(key, value).apply()
    }

    override fun <T> put(key: String, value: T, fileName: String) {
        val rawValue = Gson().toJson(value)
        getPrefsEditor(fileName).putString(key, rawValue).apply()
    }

    override fun remove(key: String, fileName: String) {
        getPrefsEditor(fileName).remove(key)
    }

    override fun clear(fileName: String) {
        getPrefsEditor(fileName).clear()
    }

    override fun getPrefsEditor(fileName: String): SharedPreferences.Editor {
        applyPreconditions()
        return getPrefs(fileName).edit()
    }

    override fun getPrefs(fileName: String): SharedPreferences {
        applyPreconditions()
        return app.getSharedPreferences(fileName, Context.MODE_PRIVATE)
    }

    private fun applyPreconditions() {
        require(initialised) {
            "SharedPrefsManager not initialised. Make sure you call PrefsManager.init(Application, String) before any PrefsManager operations."
        }
    }
}