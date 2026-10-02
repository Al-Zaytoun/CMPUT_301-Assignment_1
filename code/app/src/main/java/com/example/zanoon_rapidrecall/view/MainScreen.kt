package com.example.zanoon_rapidrecall.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zanoon_rapidrecall.controller.MainViewModel
import com.example.zanoon_rapidrecall.ui.theme.ZanoonRapidRecallTheme

@Composable
fun MainScreen(
    modifier: Modifier,
    viewModel: MainViewModel
) {
    var showGameLoop by remember { mutableStateOf(false) }
    var chosenInput by remember { mutableStateOf("") }

    // Show game Loop UI file if game has begun
    if (showGameLoop) {
        GameLoop(
            modifier = Modifier,
            mainViewModel = MainViewModel()
        )
    } else {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text("Welcome to the Submission of Assignment_1 - Zanoon Hassan")

                    Button(
                        modifier = Modifier.padding(8.dp),
                        onClick = {
                            val sequenceValue = chosenInput.toIntOrNull()
                            if (sequenceValue != null) {
                                viewModel.gameBegin(sequenceValue)
                            }
                            showGameLoop = true
                        }
                    ) {
                        Text("Begin")
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    OutlinedTextField(
                        value = chosenInput,
                        onValueChange = { newValue ->
                            if (newValue.all { it.isDigit() }) {
                                chosenInput = newValue
                            }
                        },
                        label = { Text("Enter Sequence (1 - 10) digits long)", fontSize = 15.sp) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    if (chosenInput.isNotBlank()) {
                        Text("You are ready to proceed")
                    } else {
                        Text("Please input a desired sequence")
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier.padding(8.dp),
                    onClick = {}
                ) {
                    Text("Log")
                }

                Button(
                    modifier = Modifier.padding(8.dp),
                    onClick = {}
                ) {
                    Text("Attempt Summary")
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    ZanoonRapidRecallTheme {
        MainScreen(
            modifier = Modifier,
            viewModel = MainViewModel()
        )
    }
}