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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.zanoon_rapidrecall.controller.MainViewModel

@Composable
fun SummaryScreen(modifier: Modifier, mainViewModel: MainViewModel, onReturn: () -> Unit) {

    val totalAttempts = mainViewModel.getTotalAttempts()
    val correctAttempts = mainViewModel.getCorrectAttempts()
    val accuracy = mainViewModel.getAccuracy()

    Column(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Gameplay Summary")
                Spacer(modifier = modifier.height(24.dp))

                Text("Total Attempts: ${totalAttempts}")
                Text("Correct Attempts: ${correctAttempts}")
                Text("Overall Accuracy: ${accuracy}%")
            }
        }

        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = modifier.padding(8.dp),
                onClick = onReturn
            ) {
                Text("Return")
            }
        }
    }
}