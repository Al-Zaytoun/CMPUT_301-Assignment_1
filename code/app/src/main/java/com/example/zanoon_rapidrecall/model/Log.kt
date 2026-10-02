package com.example.zanoon_rapidrecall.model

data class Log(
    /*
    * Class Log
    * Purpose: Data class to hold the attributes necessary for the logging feature
    * Rationale: Every log must contain these exact attributes, meaning it makes the mose sense to house all the necessary attributes onto a single data-class
    * */
    val sequenceLength: Int,
    val targetSequence: List<Int>,
    val correct: Boolean,
    val userInput: List<Int>,
    val timestamp: Long
)
