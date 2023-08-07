package com.example.myapplication.datasource

import android.app.Application
import com.example.myapplication.entity.Student


class MyApplicationRepository(application: Application) {
    private val studentDao = MyApplicationRoomDatabase.getDatabase(application)!!.studentDao()

    suspend fun insert(student: Student){
        studentDao.insertSingleStudent(student)
    }

    suspend fun insertMultipleStudents(students: List<Student>){
        studentDao.insertMultipleStudents(students)
    }

    fun getAllStudents() = studentDao.getAllStudents()

}