package com.example.zanoon_rapidrecall.controller

import com.example.zanoon_rapidrecall.model.Log
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Logging {
    /*
    * Class Logging
    * Purpose: Controller-level class which connects the Log data-class model onto the LogScreen View. Acts as the bridge to allow cohesion between the log class and the LogScreen
    * design rationale: Intended to ensure the UI does not get access to the data necessary for outputting the UI. all data for logging must pass through this class
    *
    * */
    private val logs = mutableListOf<Log>()
    fun addLog(
        sequenceLength: Int,
        targetSequence: List<Int>,
        correct: Boolean,
        userInput: List<Int>,
        timestamp: Long
    ) {
        logs.add(
            Log(
                sequenceLength = sequenceLength,
                targetSequence = targetSequence.toList(),
                correct = correct,
                userInput = userInput.toList(),
                timestamp = timestamp
            )
        )
    }
    fun getLogs(): List<Log> {
        return logs
    }

    fun convertTimestamp(timestamp: Long): String {
        val formatter = SimpleDateFormat(
            "MMM d, yyyy - h:mm a",
            Locale.getDefault()
        )

        return formatter.format(Date(timestamp))
    }
}