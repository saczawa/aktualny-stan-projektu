package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.ui.MainActivity


class ChoiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val studentButton: Button = findViewById(R.id.studentButton)
        val teacherButton: Button = findViewById(R.id.teacherButton)
        val languageSpinner: Spinner = findViewById(R.id.languageSpinner)

        val languages = resources.getStringArray(R.array.languages) //define your languages in strings.xml

        languageSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, languages)
        languageSpinner.setSelection(getUserLanguage()) // set previously chosen language

        languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                setUserLanguage(position) // save chosen language
            }
        }

        studentButton.setOnClickListener {
            setUserChoice("student")
            startMainActivity()
        }

        teacherButton.setOnClickListener {
            setUserChoice("teacher")
            startMainActivity()
        }
    }

    private fun setUserChoice(choice: String) {
        val sharedPref = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putString("user_choice", choice)
            apply()
        }
    }

    private fun setUserLanguage(language: Int) {
        val sharedPref = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putInt("user_language", language)
            apply()
        }
    }

    private fun getUserLanguage(): Int {
        val sharedPref = getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
        return sharedPref.getInt("user_language", 0)
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
