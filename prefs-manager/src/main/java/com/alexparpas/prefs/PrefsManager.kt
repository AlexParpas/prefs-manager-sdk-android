package com.alexparpas.prefs

import android.app.Application
import android.content.SharedPreferences

interface PrefsManager {
    var defaultFileName: String

    fun init(app: Application, defaultFileName: String)

    fun getString(key: String, defaultValue: String? = null, fileName: String = defaultFileName): String?

    fun getSet(key: String, defaultValue: Set<String>? = null, fileName: String = defaultFileName): Set<String>?

    fun getInt(key: String, defaultValue: Int = -1, fileName: String = defaultFileName): Int?

    fun getLong(key: String, defaultValue: Long = -1, fileName: String = defaultFileName): Long?

    fun getFloat(key: String, defaultValue: Float = -1f, fileName: String = defaultFileName): Float?

    fun getBoolean(key: String, defaultValue: Boolean = false, fileName: String = defaultFileName): Boolean?

    fun <T> getObject(key: String, clazz: Class<T>, defaultValue: T? = null, fileName: String = defaultFileName): T?

    fun put(key: String, value: String, fileName: String = defaultFileName)

    fun put(key: String, value: Set<String>, fileName: String = defaultFileName)

    fun put(key: String, value: Int, fileName: String = defaultFileName)

    fun put(key: String, value: Long, fileName: String = defaultFileName)

    fun put(key: String, value: Float, fileName: String = defaultFileName)

    fun put(key: String, value: Boolean, fileName: String = defaultFileName)

    fun <T> put(key: String, value: T, fileName: String = defaultFileName)

    fun remove(key: String, fileName: String = defaultFileName)

    fun clear(fileName: String = defaultFileName)

    fun getPrefsEditor(fileName: String = defaultFileName): SharedPreferences.Editor

    fun getPrefs(fileName: String = defaultFileName): SharedPreferences
}