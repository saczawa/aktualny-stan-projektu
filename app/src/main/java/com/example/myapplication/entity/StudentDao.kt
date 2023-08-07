package com.example.myapplication.entity

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.entity.Student
import java.time.DayOfWeek
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

    @Insert
    suspend fun insertSingleStudent(student: Student)

    @Insert
    suspend fun insertMultipleStudents(studentList: List<Student>)

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("DELETE FROM students")
    suspend fun deleteAll()

    @Query("SELECT * FROM students")
    fun getAllStudents(): LiveData<List<Student>>

    @Query("SELECT * FROM students ORDER BY firstName ASC")
    fun getStudentsOrderedByFirstName(): Flow<List<Student>>

    @Query("SELECT * FROM students ORDER BY lastName  ASC")
    fun getStudentsOrderedByLastName(): Flow<List<Student>>

    @Query("SELECT * FROM students ORDER BY phoneNumber ASC")
    fun getStudentsOrderedByPhoneNumber(): Flow<List<Student>>

    @Query("SELECT * FROM students WHERE firstName = :firstName AND lastName = :lastName")
    fun findStudentByName(firstName: String, lastName: String): LiveData<List<Student>>

    @Query("SELECT * FROM students WHERE day = :dayOfWeek")
    fun findStudentsByDay(dayOfWeek: DayOfWeek): LiveData<List<Student>>

}