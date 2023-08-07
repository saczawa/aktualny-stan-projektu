package com.example.myapplication.datasource.util

import androidx.room.TypeConverter
import com.example.myapplication.entity.Gender

object GenderConverter {
    @TypeConverter
    @JvmStatic
    fun toString(gender: Gender) = gender.toString()

    @TypeConverter
    @JvmStatic
    fun toGender(gender: String) = Gender.valueOf(gender)
}