package com.example.zanoon_rapidrecall.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.zanoon_rapidrecall.controller.MainViewModel
import com.example.zanoon_rapidrecall.ui.theme.ZanoonRapidRecallTheme

@Composable
fun LogScreen(modifier: Modifier, mainViewModel: MainViewModel, onReturn: () -> Unit
) {
    val logs = mainViewModel.getLogs()
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            if (logs.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No attempts logged yet.")
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(logs) { log ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        ) {
                            Text("Sequence Length: ${log.sequenceLength}")
                            Text(
                                "Target Sequence: ${
                                    log.targetSequence.joinToString(" ")
                                }"
                            )
                            Text(
                                "User Input: ${
                                    log.userInput.joinToString(" ")
                                }"
                            )
                            Text(
                                "Correct: ${
                                    if (log.correct) "Yes" else "No"
                                }"
                            )
                            Text("Timestamp: ${log.timestamp}")
                        }
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
fun LogScreenPreview() {
    val previewViewModel = MainViewModel()

    previewViewModel.gameBegin(3)

    val target = previewViewModel.getGeneratedNums()

    previewViewModel.submitSequence(target)

    ZanoonRapidRecallTheme {
        LogScreen(
            modifier = Modifier,
            mainViewModel = previewViewModel,
            onReturn = {}
        )
    }
}