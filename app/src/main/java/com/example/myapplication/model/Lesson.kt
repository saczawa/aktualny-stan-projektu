package com.example.myapplication.model

import java.time.DayOfWeek

data class Lesson(
    val id_number: Int,
    var name: String,
    val surname: String,
    var titleImage: Int,
    val time: Int,
    val daysOfWeek: List<DayOfWeek>,
    val price: Int,
    val mobile_number: Int,
    val email : String

)
