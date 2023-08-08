package com.example.myapplication.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime

@Entity(tableName = "students")
data class Student(
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "",

    @ColumnInfo(name = "phoneNumberParent1", defaultValue = "")
    val phoneNumberParent1: String,

    @ColumnInfo(name = "phoneNumberParent2", defaultValue = "")
    val phoneNumberParent2: String,

    @ColumnInfo(name = "gender", defaultValue = "Gender.MALE")
    val gender: Gender = Gender.MALE,

    @ColumnInfo(name = "day", defaultValue =  "DayOfWeek.SUNDAY")
    val day: DayOfWeek = DayOfWeek.SUNDAY,

    @ColumnInfo(name = "hour", defaultValue = "LocalTime.of(10,0)")
    val hour: LocalTime = LocalTime.of(10,0),

    @ColumnInfo(name = "debt", defaultValue = "0")
    val debt: Int,

    @ColumnInfo(name = "price", defaultValue = "0")
    val price: Int,

    @ColumnInfo(name = "school", defaultValue = "")
    val school: String,

    @ColumnInfo(name = "year", defaultValue = "0")
    val year: Int,

    @ColumnInfo(name = "howLongStudent", defaultValue = "0")
    val howLongStudent: LocalDateTime = LocalDateTime.now(),

    @ColumnInfo(name = "start_date")
    var startDate: LocalDateTime = LocalDateTime.now(),

    @ColumnInfo(name = "main_image_path")
    var imagePath: String = "",

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
//fun isNew(): Boolean {
//    val today = LocalDate.now()
//    val fromDays = dob.until(today, ChronoUnit.DAYS)
//
//    return (fromDays < 365)
//}