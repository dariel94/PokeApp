package com.dariel94.android.pokeapp.data.database.model

import androidx.room.TypeConverter
import com.dariel94.android.pokeapp.domain.model.Ability
import com.dariel94.android.pokeapp.domain.model.EvolutionChain
import com.dariel94.android.pokeapp.domain.model.Stat
import com.dariel94.android.pokeapp.domain.model.Variety
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * Created by dariel94 on 31/10/2021.
 */
object Converters {

    @TypeConverter
    fun stringListToJson(value: List<String>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToStringList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun statListToJson(list: List<Stat>?): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Stat?>?>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun jsonToStatList(json: String?): List<Stat> {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Stat?>?>() {}.type
        return gson.fromJson<List<Stat>>(json, type)
    }

    @TypeConverter
    fun abilityListToJson(list: List<Ability>?): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Ability?>?>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun jsonToAbilityList(json: String?): List<Ability> {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Ability?>?>() {}.type
        return gson.fromJson<List<Ability>>(json, type)
    }

    @TypeConverter
    fun varietyListToJson(list: List<Variety>?): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Variety?>?>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun jsonToVarietyList(json: String?): List<Variety> {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Variety?>?>() {}.type
        return gson.fromJson<List<Variety>>(json, type)
    }

    @TypeConverter
    fun evolutionChainToJson(evolutionChain: EvolutionChain): String {
        val gson = Gson()
        val type: Type = object : TypeToken<EvolutionChain?>() {}.type
        return gson.toJson(evolutionChain, type)
    }

    @TypeConverter
    fun jsonToEvolutionChain(json: String?): EvolutionChain {
        val gson = Gson()
        val type: Type = object : TypeToken<EvolutionChain?>() {}.type
        return gson.fromJson<EvolutionChain>(json, type)
    }
}