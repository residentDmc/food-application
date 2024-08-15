package refixbody.fix.foodapplication.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import refixbody.fix.foodapplication.data.local.entities.Food
import refixbody.fix.foodapplication.data.local.entities.Person

class Converters {
    @TypeConverter
    fun fromListToString(list: List<Int>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromStringToList(value: String?): List<Int>? {
        val listType = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromPersonList(value: List<Person>?): String? {
        val gson = Gson()
        val type = object : TypeToken<List<Person>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toPersonList(value: String?): List<Person>? {
        val gson = Gson()
        val type = object : TypeToken<List<Person>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromFoodList(value: List<Food>?): String? {
        val gson = Gson()
        val type = object : TypeToken<List<Food>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toFoodList(value: String?): List<Food>? {
        val gson = Gson()
        val type = object : TypeToken<List<Food>>() {}.type
        return gson.fromJson(value, type)
    }


}
