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
fun SummaryScreen(modifier: Modifier, mainViewModel: MainViewModel, onReturn: () -> Unit) {

    val totalAttempts = mainViewModel.getTotalAttempts()
    val correctAttempts = mainViewModel.getCorrectAttempts()
    val accuracy = mainViewModel.getAccuracy()

    Column(modifier = modifier.fillMaxSize()) {
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
                    text = "Gameplay Summary",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Your performance across all attempts",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Total Attempts: $totalAttempts",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Correct Attempts: $correctAttempts",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Overall Accuracy: ${"%.1f".format(accuracy)}%",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        HorizontalDivider()
        Spacer(modifier = Modifier.height(12.dp))


        Row(
            modifier = modifier.fillMaxWidth(),
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
fun SummaryScreenPreview() {
    ZanoonRapidRecallTheme {
        SummaryScreen(
            modifier = Modifier,
            mainViewModel = MainViewModel(),
            onReturn = {}
        )
    }
}