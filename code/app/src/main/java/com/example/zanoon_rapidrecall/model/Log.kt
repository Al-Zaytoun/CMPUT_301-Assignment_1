package com.example.zanoon_rapidrecall.model

data class Log(
    val sequenceLength: Int,
    val targetSequence: List<Int>,
    val correct: Boolean,
    val userInput: List<Int>,
    val timestamp: Long
)
