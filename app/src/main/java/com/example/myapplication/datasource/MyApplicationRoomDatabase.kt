package com.example.myapplication.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.datasource.util.DayOfWeekConverter
import com.example.myapplication.datasource.util.GenderConverter
import com.example.myapplication.datasource.util.LocalDateTimeConverter
import com.example.myapplication.datasource.util.LocalTimeConverter
import com.example.myapplication.entity.Student
import com.example.myapplication.entity.Gender
import com.example.myapplication.entity.StudentDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime
import androidx.room.AutoMigration

@Database(
    entities = [Student::class],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)
@TypeConverters(LocalDateTimeConverter::class, GenderConverter::class,
    DayOfWeekConverter::class, LocalTimeConverter::class)
abstract class  MyApplicationRoomDatabase : RoomDatabase() {

    abstract fun studentDao(): StudentDao

    companion object {
        private var instance: MyApplicationRoomDatabase? = null
        private val coroutineScope = CoroutineScope(Dispatchers.IO)

        @Synchronized
        fun getDatabase(context: Context): MyApplicationRoomDatabase? {
            if (instance == null) {
                instance =
                    Room.databaseBuilder<MyApplicationRoomDatabase>(
                        context.applicationContext,
                        MyApplicationRoomDatabase::class.java,
                        "myapplication_database"
                    )
                        .allowMainThreadQueries()
                        .addCallback(roomDatabaseCallback(context))
                        //.addMigrations(MIGRATION_1_2, MIGRATION_2_3
                        .build()
            } // if
            return instance
        }

        private fun roomDatabaseCallback(context: Context): Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

                    coroutineScope.launch {
                        populateDatabase(context, getDatabase(context)!!)
                    }
                }
            }
        }

        private suspend fun populateDatabase(
            context: Context,
            instance: MyApplicationRoomDatabase
        ) {
            val newStudent = LocalDateTime.now().minusDays(365)
            val oldStudent = LocalDateTime.now()


            val student1 = Student(
                "Norbert",
                "Gierczak",
                "1234567654",
                "1234567654",
                "1234567654",
                Gender.MALE,
                DayOfWeek.SUNDAY,
                LocalTime.of(10, 0),
                0,
                50,
                "Secondary",
                1,
                newStudent,
                LocalDateTime.now(),
                "a.png",
                0
            )
            val student2 = Student(
                "Norbert",
                "Gierczak",
                "1234567654",
                "1234567654",
                "1234567654",
                Gender.MALE,
                DayOfWeek.SUNDAY,
                LocalTime.of(10, 0),
                0,
                50,
                "Secondary",
                1,
                oldStudent,
                LocalDateTime.now(),
                "a.png",
                0
            )
            val studentList = mutableListOf(
                student1,
                student2
            )


            val dao = instance.studentDao()
            dao.insertMultipleStudents(studentList)
        }
    }
}