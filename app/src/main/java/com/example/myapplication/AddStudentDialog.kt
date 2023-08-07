package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.AlertDialog
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.material.Button

@Composable
fun AddStudentDialog(
    state: StudentState,
    onEvent: (StudentEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onEvent(StudentEvent.HideDialog)
        },
        title = { Text(text = "Add student") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.firstName,
                    onValueChange = {
                        onEvent(StudentEvent.SetFirstName(it))
                    },
                    placeholder = {
                        Text(text = "First name")
                    }
                )
                TextField(
                    value = state.lastName,
                    onValueChange = {
                        onEvent(StudentEvent.SetLastName(it))
                    },
                    placeholder = {
                        Text(text = "Last name")
                    }
                )
                TextField(
                    value = state.phoneNumber,
                    onValueChange = {
                        onEvent(StudentEvent.SetPhoneNumber(it))
                    },
                    placeholder = {
                        Text(text = "Phone number")
                    }
                )
            }
        },
        buttons = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = {
                    onEvent(StudentEvent.SaveStudent)
                }) {
                    Text(text = "Save")
                }
            }
        }
    )
}