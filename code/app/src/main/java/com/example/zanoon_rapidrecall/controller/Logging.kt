package com.example.zanoon_rapidrecall.controller

import com.example.zanoon_rapidrecall.model.Log

class Logging {
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
                targetSequence = targetSequence,
                correct = correct,
                userInput = userInput,
                timestamp = timestamp
            )
        )
    }
    fun getLogs() : List<Log> {
        return logs
    }
}