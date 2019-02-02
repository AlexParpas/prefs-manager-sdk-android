package com.alexparpas.prefs

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.support.v4.util.Preconditions
import com.google.gson.Gson

object PrefsManager {
    private lateinit var app: Application
    lateinit var defaultFileName: String
    private var initialised = false

    fun init(app: Application, defaultFileName: String) {
        this.app = app
        this.defaultFileName = defaultFileName
        initialised = true
    }

    fun getPrefsEditor(fileName: String = defaultFileName) = getPrefs(fileName).edit()

    fun getPrefs(fileName: String = defaultFileName) = app.getSharedPreferences(fileName, Context.MODE_PRIVATE)

    fun getString(key: String, defaultValue: String? = null, fileName: String = defaultFileName): String? {
        return getPrefs(fileName).getString(key, defaultValue)
    }

    fun getSet(key: String, defaultValue: Set<String>? = null, fileName: String = defaultFileName): Set<String>? {
        return getPrefs(fileName).getStringSet(key, defaultValue)
    }

    fun getInt(key: String, defaultValue: Int = -1, fileName: String = defaultFileName): Int? {
        return getPrefs(fileName).getInt(key, defaultValue)
    }

    fun getLong(key: String, defaultValue: Long = -1, fileName: String = defaultFileName): Long? {
        return getPrefs(fileName).getLong(key, defaultValue)
    }

    fun getFloat(key: String, defaultValue: Float = -1f, fileName: String = defaultFileName): Float? {
        return getPrefs(fileName).getFloat(key, defaultValue)
    }

    fun getBoolean(key: String, defaultValue: Boolean = false, fileName: String = defaultFileName): Boolean? {
        return getPrefs(fileName).getBoolean(key, defaultValue)
    }

    inline fun <reified T> getPOJO(key: String, defaultValue: T? = null, fileName: String = defaultFileName): T {
        val rawValue = getPrefs(fileName).getString(key, defaultFileName)
        return Gson().fromJson<T>(rawValue, T::class.java)
    }

    fun put(key: String, value: String, fileName: String = defaultFileName) {
        getPrefsEditor(fileName).putString(key, value).apply()
    }

    fun put(key: String, value: Set<String>, fileName: String = defaultFileName) {
        getPrefsEditor(fileName).putStringSet(key, value).apply()
    }

    fun put(key: String, value: Int, fileName: String = defaultFileName) {
        getPrefsEditor(fileName).putInt(key, value).apply()
    }

    fun put(key: String, value: Long, fileName: String = defaultFileName) {
        getPrefsEditor(fileName).putLong(key, value).apply()
    }

    fun put(key: String, value: Float, fileName: String = defaultFileName) {
        getPrefsEditor(fileName).putFloat(key, value).apply()
    }

    fun put(key: String, value: Boolean, fileName: String = defaultFileName) {
        getPrefsEditor(fileName).putBoolean(key, value).apply()
    }

    fun <T> put(key: String, value : T, fileName: String = defaultFileName) {
        val rawValue = Gson().toJson(value)
        getPrefsEditor(fileName).putString(key, rawValue).apply()
    }

    fun remove(key: String, fileName: String = defaultFileName) {
        getPrefsEditor(fileName).remove(key)
    }

    fun clear(fileName: String = defaultFileName) {
        getPrefsEditor(fileName).clear()
    }

    @SuppressLint("RestrictedApi") //TODO Investigate
    private fun applyPreconditions() {
        Preconditions.checkArgument(
                initialised,
                "SharedPrefsManager not initialised. Make sure you call SharedPrefsManager.init() before any operations."
        )
    }
}