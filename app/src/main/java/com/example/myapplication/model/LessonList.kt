package com.example.myapplication.model


import com.example.myapplication.R
import java.time.DayOfWeek
import java.util.*


class LessonList {

    val lessons = mutableListOf(
        Lesson(0, "Johny", "Smith", R.drawable.a, 10, listOf(DayOfWeek.MONDAY), 50, 123456789, "student@panda.com"),
        Lesson(1, "Anghard", "Smith", R.drawable.a,11, listOf(DayOfWeek.MONDAY), 50, 123456789,"student@panda.com"),
        Lesson(2, "Steve", "Smith", R.drawable.c,12, listOf(DayOfWeek.MONDAY), 50, 123456789, "student@panda.com"),
        Lesson(3, "William", "Smith", R.drawable.d, 13, listOf(DayOfWeek.MONDAY), 70, 123456789, "student@panda.com"),
        Lesson(4, "Amelia", "Smith", R.drawable.e, 17, listOf(DayOfWeek.SUNDAY), 60, 99653314, "student@panda.com"),
        Lesson(5, "Grace", "Smith", R.drawable.f, 17, listOf(DayOfWeek.THURSDAY), 60, 45675214, "student@panda.com"),
        Lesson(6, "Steve", "Smith", R.drawable.g, 17, listOf(DayOfWeek.SUNDAY), 60, 456982365, "student@panda.com"),
        Lesson(7, "Arthur", "Smith", R.drawable.h, 12, listOf(DayOfWeek.SUNDAY), 60, 456823789, "student@panda.com"))


    fun getAll(): MutableList<Lesson> {
        return lessons
    }

    fun getLessonsForDate(date: Date): MutableList<Lesson> {
        val calendar = Calendar.getInstance()
        calendar.time = date
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        // Map Calendar.DAY_OF_WEEK values to their corresponding DayOfWeek values
        val mappedDayOfWeek = when (dayOfWeek) {
            Calendar.SUNDAY -> DayOfWeek.SUNDAY
            Calendar.MONDAY -> DayOfWeek.MONDAY
            Calendar.TUESDAY -> DayOfWeek.TUESDAY
            Calendar.WEDNESDAY -> DayOfWeek.WEDNESDAY
            Calendar.THURSDAY -> DayOfWeek.THURSDAY
            Calendar.FRIDAY -> DayOfWeek.FRIDAY
            Calendar.SATURDAY -> DayOfWeek.SATURDAY
            else -> null
        }

        // Return an empty list if the mappedDayOfWeek is null
        return if (mappedDayOfWeek != null) {
            lessons.filter { it.daysOfWeek.contains(mappedDayOfWeek) }.toMutableList()
        } else {
            mutableListOf()
        }
    }
}