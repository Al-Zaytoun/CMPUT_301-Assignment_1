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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import com.example.zanoon_rapidrecall.controller.MainViewModel
import com.example.zanoon_rapidrecall.ui.theme.ZanoonRapidRecallTheme

@Composable
fun MainScreen(
    modifier: Modifier,
    viewModel: MainViewModel
) {
    var showGameLoop by remember { mutableStateOf(false) }
    var showSummary by remember { mutableStateOf(false) }
    var showLog by remember { mutableStateOf(false) }
    var chosenInput by remember { mutableStateOf("") }

    if (showSummary) {
        SummaryScreen(
            modifier = modifier,
            mainViewModel = viewModel,
            onReturn = {
                showSummary = false
            }
        )
        return
    }

    if (showLog) {
        LogScreen(
            modifier = modifier,
            mainViewModel = viewModel,
            onReturn = {
                showLog = false
            }
        )
        return
    }

    if (showGameLoop) {
        GameLoop(
            modifier = modifier,
            mainViewModel = viewModel,
            onReturn = {
                showGameLoop = false
            }
        )
        return
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Rapid Recall",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "Assignment 1 - Zanoon Hassan",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(
                    modifier = Modifier.height(32.dp)
                )

                OutlinedTextField(
                    value = chosenInput,
                    onValueChange = { newValue ->
                        if (
                            newValue.all { it.isDigit() } &&
                            newValue.length <= 2
                        ) {
                            chosenInput = newValue
                        }
                    },
                    label = {
                        Text("Sequence length")
                    },
                    placeholder = {
                        Text("1 - 10")
                    },
                    supportingText = {
                        Text("Choose how many numbers you want to memorize")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    singleLine = true
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                Button(
                    onClick = {
                        val sequenceValue = chosenInput.toIntOrNull()

                        if (
                            sequenceValue != null &&
                            sequenceValue in 1..10
                        ) {
                            viewModel.gameBegin(sequenceValue)
                            showGameLoop = true
                            chosenInput = ""
                        }
                    },
                    enabled = chosenInput.toIntOrNull() in 1..10
                ) {
                    Text("Begin")
                }

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                if (chosenInput.isBlank()) {
                    Text(
                        text = "Enter a sequence length to begin",
                        style = MaterialTheme.typography.bodySmall
                    )
                } else if (chosenInput.toIntOrNull() !in 1..10) {
                    Text(
                        text = "Please enter a number from 1 to 10",
                        style = MaterialTheme.typography.bodySmall
                    )
                } else {
                    Text(
                        text = "Ready to begin",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }

        HorizontalDivider()

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                12.dp,
                Alignment.CenterHorizontally
            )
        ) {

            OutlinedButton(
                onClick = {
                    showLog = true
                }
            ) {
                Text("Log")
            }

            OutlinedButton(
                onClick = {
                    showSummary = true
                }
            ) {
                Text("Attempt Summary")
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