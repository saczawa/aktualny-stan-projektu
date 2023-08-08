package com.example.myapplication.navigation

sealed class Screen(val route: String) {
    object Student : Screen("students")
    object Plan : Screen("plan")
}

val screens = listOf(
    Screen.Student,
    Screen.Plan
)