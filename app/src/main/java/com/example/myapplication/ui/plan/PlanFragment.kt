package com.example.myapplication.ui.plan

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.PlanAdapter
import com.example.myapplication.model.LessonList


import java.text.SimpleDateFormat
import java.util.*

class PlanFragment : Fragment()  {
    private lateinit var currentDateTextView: TextView
    private val calendar = Calendar.getInstance()
    private var selectedDate: Date = Date()

    private lateinit var newRecyclerview : RecyclerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_plan, container, false)

        // Inicjalizacja TextView
        currentDateTextView = view.findViewById(R.id.calendar_textView)

        // Ustawienie aktualnej daty w TextView
        val dateFormat = SimpleDateFormat("EEEE, dd/MM", Locale.getDefault())
        selectedDate = Calendar.getInstance().time
//        val currentDate = dateFormat.format(calendar.time)
        currentDateTextView.text = dateFormat.format(selectedDate)

        // Dodanie onClickListener do TextView, który otwiera DatePickerDialog
        currentDateTextView.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    // Ustawienie wybranej daty w TextView
                    val newCalendar = Calendar.getInstance().apply {
                        set(year, month, dayOfMonth)
                    }
                    selectedDate = newCalendar.time
                    currentDateTextView.text = dateFormat.format(selectedDate)

                    // Aktualizacja adaptera z wyświetlanymi lekcjami dla wybranej daty
                    val lessonsList = LessonList() // pobranie danych z serwera lub bazy danych
                    val filteredLessons = lessonsList.getLessonsForDate(selectedDate)
                    newRecyclerview.adapter = PlanAdapter(filteredLessons)
                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()

        }

        // Przycisk "w lewo" zmniejsza datę o jeden dzień i aktualizuje TextView i adapter
        view.findViewById<View>(R.id.left_button).setOnClickListener {
            val newCalendar = Calendar.getInstance().apply {
                time = selectedDate
                add(Calendar.DATE, -1)
            }
            selectedDate = newCalendar.time
            currentDateTextView.text = dateFormat.format(selectedDate)
            val lessonsList = LessonList() // pobranie danych z serwera lub bazy danych
            val filteredLessons = lessonsList.getLessonsForDate(selectedDate)
            newRecyclerview.adapter = PlanAdapter(filteredLessons)
        }

        // Przycisk "w prawo" zwiększa datę o jeden dzień i aktualizuje TextView i adapter
        view.findViewById<View>(R.id.right_button).setOnClickListener {
            val newCalendar = Calendar.getInstance().apply {
                time = selectedDate
                add(Calendar.DATE, 1)
            }
            selectedDate = newCalendar.time
            currentDateTextView.text = dateFormat.format(selectedDate)
            val lessonsList = LessonList() // pobranie danych z serwera lub bazy danych
            val filteredLessons = lessonsList.getLessonsForDate(selectedDate)
            newRecyclerview.adapter = PlanAdapter(filteredLessons)
        }




        // Dodanie obserwatora zmiany tekstu do TextView
        currentDateTextView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // Aktualizacja adaptera z wyświetlanymi lekcjami dla wybranej daty
                val lessonsList = LessonList() // pobranie danych z serwera lub bazy danych
                val filteredLessons = lessonsList.getLessonsForDate(selectedDate)
                newRecyclerview.adapter = PlanAdapter(filteredLessons)
            }



            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })




        val lessonsList = LessonList() // pobranie danych z serwera lub bazy danych
        val filteredLessons = lessonsList.getLessonsForDate(selectedDate)
        newRecyclerview = view.findViewById(R.id.planRecyclerView)
        newRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        newRecyclerview.setHasFixedSize(true)
        newRecyclerview.adapter = PlanAdapter(filteredLessons)




        return view
    }


}
