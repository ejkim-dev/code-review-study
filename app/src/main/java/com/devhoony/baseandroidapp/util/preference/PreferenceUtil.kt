package com.devhoony.baseandroidapp.util.preference

import android.content.Context
import androidx.annotation.StringRes
import java.util.ArrayList
import java.util.HashMap

/**
 * simple preference
 */
class PreferenceUtil(context: Context) : BasePreferenceUtil(context) {
    fun containPreferences(key: String): Boolean {
        return getContains(key)
    }

    fun containPreferences(@StringRes res: Int): Boolean {
        return getContains(mContext.getString(res))
    }

    fun setStringPref(key: String, value: String) {
        putString(key, value)
    }

    fun getStringPref(key: String, defaultValue: String?): String? {
        return getString(key, defaultValue)
    }

    fun setIntPref(key: String, value: Int) {
        putInt(key, value)
    }

    fun getIntPref(key: String, defaultValue: Int): Int {
        return getInt(key, defaultValue)
    }

    fun setDoublePref(key: String, value: Double) {
        putDouble(key, value)
    }

    fun getDoublePref(key: String, defaultValue: Double): Double {
        return getDouble(key, defaultValue)
    }


    fun setBooleanPref(key: String, value: Boolean) {
        putBoolean(key, value)
    }

    fun getBooleanPref(key: String, defaultValue: Boolean): Boolean {
        return getBoolean(key, defaultValue)
    }

    fun setStringListPref(key: String, value: ArrayList<String>) {
        putStringList(key, value)
    }

    fun getStringListPref(key: String): ArrayList<String> {
        return getStringList(key)
    }

    fun setStringMapPref(key: String, value: HashMap<String, String?>) {
        putStringMap(key, value)
    }

    fun getStringMapPref(key: String): HashMap<String, String?> {
        return getStringMap(key)
    }

    fun setStringSetPref(key: String, value: HashSet<String?>) {
        putStringSet(key, value)
    }

    fun getStringSetPref(key: String): HashSet<String?> {
        return getStringSet(key)
    }


    fun deletePref(key: String) {
        delete(key)
    }
}