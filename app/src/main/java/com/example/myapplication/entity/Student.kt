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
    val phoneNumberParent1: String ="",
    val phoneNumberParent2: String ="",
    val gender: Gender = Gender.MALE,
    val day: DayOfWeek = DayOfWeek.SUNDAY,
    val hour: LocalTime = LocalTime.of(10,0),
    val debt: Int = 0,
    val price: Int = 0,
    val school: String ="",
    val year: Int = 0,
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