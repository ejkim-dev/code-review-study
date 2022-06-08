package com.devhoony.baseandroidapp.util.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import java.util.*
import kotlin.collections.HashSet
import kotlin.collections.indices
import kotlin.collections.set

/**
 * simple base preference
 */
open class BasePreferenceUtil(val mContext: Context) {

    private val masterKey: MasterKey =
        MasterKey.Builder(mContext, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

    private var sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        mContext,
        "1mHomeDance",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )


    protected fun getContains(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

    protected fun putString(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    protected fun getString(key: String, defaultValue: String?): String? {
        return sharedPreferences.getString(key, defaultValue)
    }


    protected fun putInt(key: String, value: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    protected fun getInt(key: String, value: Int): Int {
        return sharedPreferences.getInt(key, value)
    }

    protected fun putDouble(key: String, value: Double) {
        val editor = sharedPreferences.edit()
        editor.putFloat(key, value.toFloat())
        editor.apply()
    }

    protected fun getDouble(key: String, value: Double): Double {
        return sharedPreferences.getFloat(key, value.toFloat()).toDouble()
    }

    protected fun putBoolean(key: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    protected fun getBoolean(key: String, value: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, value)
    }

    protected fun putStringList(key: String, value: ArrayList<String>) {
        val editor = sharedPreferences.edit()

        editor.putString(key, "PreferenceList")
        editor.putInt(key + "_list_size", value.size) /*sKey is an array*/

        for (i in value.indices) {
            editor.remove(key + "_list_" + i)
            editor.putString(key + "_list_" + i, value[i])
        }

        editor.apply()
    }

    protected fun getStringList(key: String): ArrayList<String> {
        val outputList = ArrayList<String>()
        val size = sharedPreferences.getInt(key + "_list_size", 0)

        for (i in 0 until size) {
            val value = sharedPreferences.getString(key + "_list_" + i, null)

            if (value != null) {
                outputList.add(value)
            }
        }

        return outputList
    }

    protected fun putStringMap(key: String, value: HashMap<String, String?>) {
        val editor = sharedPreferences.edit()

        editor.putString(key, "PreferenceMap")
        editor.putInt(key + "_map_size", value.size) /*sKey is an array*/

        val keys = value.keys.iterator()
        var i = 0
        while (keys.hasNext()) {
            val mapKey = keys.next()
            editor.remove(mapKey + "_map_" + i + "_key")
            editor.putString(mapKey + "_map_" + i + "_key", mapKey)
            editor.remove(mapKey + "_map_" + i + "_value")
            editor.putString(mapKey + "_map_" + i + "_value", value[mapKey])
            i++
        }

        editor.apply()
    }

    protected fun getStringMap(key: String): HashMap<String, String?> {
        val outputMap = HashMap<String, String?>()
        val size = sharedPreferences.getInt(key + "_map_size", 0)

        for (i in 0 until size) {
            val mapKey = sharedPreferences.getString(key + "_map_" + i + "_key", null)

            if (mapKey != null) {
                val value = sharedPreferences.getString(key + "_map_" + i + "_value", null)
                outputMap[mapKey] = value
            }
        }

        return outputMap
    }

    protected fun putStringSet(key: String, value: HashSet<String?>) {
        val editor = sharedPreferences.edit()

        editor.putString(key, "PreferenceSet")
        editor.putInt(key + "_set_size", value.size) /*sKey is an array*/

        val keys = value.iterator()
        var i = 0
        while (keys.hasNext()) {
            val setValue = keys.next()
            editor.remove(key + "_set_" + i)
            editor.putString(key + "_set_" + i, setValue)
            i++
        }

        editor.apply()
    }

    protected fun getStringSet(key: String): HashSet<String?> {
        val outputSet = HashSet<String?>()
        val size = sharedPreferences.getInt(key + "_set_size", 0)

        for (i in 0 until size) {
            val setValue = sharedPreferences.getString(key + "_set_" + i, null)
            outputSet.add(setValue)
        }

        return outputSet
    }

    protected fun delete(key: String) {
        val editor = sharedPreferences.edit()

        val all = sharedPreferences.all
        if (all[key] is String) {
            if (getString(key, "") == "PreferenceList") {
                val size = sharedPreferences.getInt(key + "_list_size", 0)
                for (i in 0 until size) {
                    editor.remove(key + "_list_" + i)
                }
            } else if (getString(key, "") == "PreferenceMap") {
                val size = sharedPreferences.getInt(key + "_map_size", 0)
                for (i in 0 until size) {
                    editor.remove(key + "_map_" + i + "_key")
                    editor.remove(key + "_map_" + i + "_value")
                }
            }
        } else {
            editor.remove(key)
        }

        editor.remove(key)
        editor.apply()
    }
}