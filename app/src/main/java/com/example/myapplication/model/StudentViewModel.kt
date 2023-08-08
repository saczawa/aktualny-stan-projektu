package com.example.myapplication.model

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.myapplication.SortType
import com.example.myapplication.StudentEvent
import com.example.myapplication.StudentState
import com.example.myapplication.entity.Gender
import com.example.myapplication.entity.Student
import com.example.myapplication.entity.StudentDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalTime


class StudentViewModel(
    private val dao: StudentDao
): ViewModel() {

    private val _sortType = MutableStateFlow(SortType.FIRST_NAME)
    private val _students = _sortType
        .flatMapLatest { sortType ->
            when(sortType) {
                SortType.FIRST_NAME -> dao.getStudentsOrderedByFirstName()
                SortType.LAST_NAME -> dao.getStudentsOrderedByLastName()
                SortType.PHONE_NUMBER -> dao.getStudentsOrderedByPhoneNumber()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(StudentState())
    val state = combine(_state, _sortType, _students) { state, sortType, students ->
        state.copy(
            students = students,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), StudentState())

    fun onEvent(event: StudentEvent) {
        when(event) {
            is StudentEvent.DeleteStudent -> {
                viewModelScope.launch {
                    dao.deleteStudent(event.student)
                }
            }
            StudentEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingStudent = false
                )

                }
            }
            StudentEvent.SaveStudent -> {
                val stateValue = state.value
                val firstName = stateValue.firstName
                val lastName = stateValue.lastName
                val phoneNumber = stateValue.phoneNumber
                val phoneNumberParent1 = stateValue.phoneNumberParent1
                val phoneNumberParent2 = stateValue.phoneNumberParent2
                val gender = stateValue.gender
                val day = stateValue.day
                val hour = stateValue.hour
                val price = stateValue.price
                val school = stateValue.school
                val year = stateValue.year
                val imagePath = stateValue.imagePath


                if(firstName.isNotBlank() && lastName.isNotBlank() && phoneNumber.isNotBlank()) {
                    val student = Student(
                        firstName = firstName,
                        lastName = lastName,
                        phoneNumber = phoneNumber,
                        phoneNumberParent1 = phoneNumberParent1,
                        phoneNumberParent2 = phoneNumberParent2,
                        gender = gender,
                        day = day,
                        hour = hour,
                        price = price,
                        school = school,
                        year = year,
                        imagePath = imagePath

                    )
                    viewModelScope.launch {
                        dao.insertSingleStudent(student)
                    }
                    _state.update { it.copy(
                        isAddingStudent = false,
                        firstName = "",
                        lastName = "",
                        phoneNumber = "",
                        phoneNumberParent1 = "",
                        phoneNumberParent2 = "",
                        gender = Gender.MALE,
                        day = DayOfWeek.SUNDAY,
                        hour = LocalTime.of(0,0),
                        price = 0,
                        school = "",
                        year = 0,
                        imagePath = "default.png"

                    )}
                }
            }
            is StudentEvent.SetDay -> {
                _state.update { it.copy(
                    day = event.day
                ) }
            }
            is StudentEvent.SetDebt -> {
                _state.update { it.copy(
                    debt = event.debt
                ) }
            }
            is StudentEvent.SetFirstName -> {
                _state.update { it.copy(
                    firstName = event.firstName
                ) }
            }
            is StudentEvent.SetGender -> {
                _state.update { it.copy(
                    gender = event.gender
                ) }
            }
            is StudentEvent.SetHour -> {
                _state.update { it.copy(
                    hour = event.hour
                ) }
            }
            is StudentEvent.SetImagePath -> {
                _state.update { it.copy(
                    imagePath = event.imagePath
                ) }
            }
            is StudentEvent.SetLastName -> {
                _state.update { it.copy(
                    lastName = event.lastName
                ) }
            }
            is StudentEvent.SetPhoneNumber -> {
                _state.update { it.copy(
                    phoneNumber = event.phoneNumber
                ) }
            }
            is StudentEvent.SetPhoneNumberParent1 -> {
                _state.update { it.copy(
                    phoneNumberParent1 = event.phoneNumberParent1
                ) }
            }
            is StudentEvent.SetPhoneNumberParent2 -> {
                _state.update { it.copy(
                    phoneNumberParent2 = event.phoneNumberParent2
                ) }
            }
            is StudentEvent.SetPrice -> {
                _state.update { it.copy(
                    price = event.price
                ) }
            }
            is StudentEvent.SetSchool -> {
                _state.update { it.copy(
                    school = event.school
                ) }
            }
            is StudentEvent.SetYear -> {
                _state.update { it.copy(
                    year = event.year
                ) }
            }
            StudentEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingStudent = true
                ) }
            }
            is StudentEvent.SortStudent -> {
                _state.update { it.copy(
                    isAddingStudent = true
                ) }
            }
        }
    }
    }
