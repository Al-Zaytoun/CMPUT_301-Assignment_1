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
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.zanoon_rapidrecall.controller.MainViewModel
import com.example.zanoon_rapidrecall.ui.theme.ZanoonRapidRecallTheme
import kotlinx.coroutines.delay

@Composable
fun GameLoop(modifier: Modifier, mainViewModel: MainViewModel, onReturn: () -> Unit) {

    val generatedNums = mainViewModel.getGeneratedNums()
    var currentIndex by remember { mutableIntStateOf(0) }
    var finishedShowing by remember { mutableStateOf(false) }
    var userInput by remember { mutableStateOf("") }
    var answerCorrect by remember { mutableStateOf<Boolean?>(null) }

    // Showing each num in a timely manner
    LaunchedEffect(Unit) {
        while (currentIndex < generatedNums.size) {
            delay(1000)  // 1 Second

            currentIndex ++
        }
        finishedShowing = true
    }
    Column(modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {

            if (!finishedShowing && currentIndex < generatedNums.size) {
                Text(
                    text = generatedNums[currentIndex].toString()
                )
            } else {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Enter the sequence you seen")

                    Spacer(modifier = Modifier.height(24.dp))

                    OutlinedTextField(
                        value = userInput,
                        onValueChange = { newValue ->
                            userInput = newValue
                        },
                        label = { Text("Sequence") },
                        placeholder = { Text(" Example 12 7 35 4")}
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(modifier = Modifier.padding(8.dp),
                        onClick = {
                            val userSequence = userInput
                                .trim()
                                .split(" ")
                                .mapNotNull { it.toIntOrNull()}

                            answerCorrect = mainViewModel.submitSequence(userSequence)
                        }) {
                        Text("Submit")
                    }
                    if (answerCorrect == true) {
                        Text("Correct")
                    } else {
                        Text("Incorrect")
                    }
                }
            }

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = onReturn
            ) {
                Text("Return")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GameLoopPreview() {
    ZanoonRapidRecallTheme {
        GameLoop(
            modifier = Modifier,
            mainViewModel = MainViewModel(),
            onReturn = {}
        )
    }
}