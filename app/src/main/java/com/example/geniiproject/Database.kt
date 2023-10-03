package com.example.geniiproject

import Deck
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/**SharedPreference is used to save the data
 *
 * Converts the
 *
 *
 *
 *
 *
 * **/

class Database(context: Context) {

    private val SAVER: SharedPreferences = context.getSharedPreferences("SAVE", AppCompatActivity.MODE_PRIVATE)
    private val gson = Gson()

    fun saveData() {
        val hashMapString: String = gson.toJson(Deck.cache) //converts the Deck Object into a json
        SAVER.edit().putString("hashString", hashMapString).apply() //key, value....
    }

    fun loadData(){

        val storedHashMapString: String? = SAVER.getString("hashString", null)
        if (storedHashMapString != null) {
            val type = object : TypeToken<MutableMap<String?, String?>?>() {}.type //fetches the type
            Deck.cache = gson.fromJson(storedHashMapString, type)
        } else {
            // Handle the case when no data is found, e.g., initialize Deck.cache to an empty HashMap
            Deck.cache = HashMap()
        }

    }



}
