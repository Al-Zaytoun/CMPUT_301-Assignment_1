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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.zanoon_rapidrecall.controller.MainViewModel
import com.example.zanoon_rapidrecall.ui.theme.ZanoonRapidRecallTheme

@Composable
fun LogScreen(
    modifier: Modifier,
    mainViewModel: MainViewModel,
    onReturn: () -> Unit
) {
    val logs = mainViewModel.getLogs()

    Column(
        modifier = modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Attempt Log",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Review your previous attempts",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {

            if (logs.isEmpty()) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No attempts logged yet.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

            } else {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    items(logs) { log ->

                        Card(
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {

                                Text(
                                    text = "Sequence Length: ${log.sequenceLength}",
                                    style = MaterialTheme.typography.titleMedium
                                )

                                Spacer(
                                    modifier = Modifier.height(8.dp)
                                )

                                Text(
                                    text = "Target: ${
                                        log.targetSequence.joinToString(" ")
                                    }"
                                )

                                Text(
                                    text = "Your Input: ${
                                        log.userInput.joinToString(" ")
                                    }"
                                )

                                Spacer(
                                    modifier = Modifier.height(8.dp)
                                )

                                Text(
                                    text = if (log.correct) {
                                        "Result: Correct"
                                    } else {
                                        "Result: Incorrect"
                                    },
                                    style = MaterialTheme.typography.titleSmall
                                )

                                Spacer(
                                    modifier = Modifier.height(8.dp)
                                )

                                Text(
                                    text = "Timestamp: ${mainViewModel.convertTimestamp(log.timestamp)}",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }
            }
        }

        HorizontalDivider()

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedButton(
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