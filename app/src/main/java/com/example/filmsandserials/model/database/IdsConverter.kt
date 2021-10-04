package com.example.filmsandserials.model.database

import androidx.room.TypeConverter

class IdsConverter {
    @TypeConverter
    fun fromList(list: List<Int>): String {
        var str = ""
        for (el in list.indices) {
            if (el != list.size - 1) {
                str += "${list[el]},"
            } else {
                str += list[el]
            }
        }
        return str
    }

    @TypeConverter
    fun toList(data: String): List<Int> {
        return data.split(",").map {
            el -> el.toInt()
        }
    }
}