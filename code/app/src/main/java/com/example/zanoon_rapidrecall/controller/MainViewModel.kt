package com.example.zanoon_rapidrecall.controller

import androidx.lifecycle.ViewModel
import com.example.zanoon_rapidrecall.model.GameGeneration
import com.example.zanoon_rapidrecall.model.Log

class MainViewModel : ViewModel() {

    private val gameGeneration = GameGeneration()
    private val logging = Logging()

    var currentNumber: Int? = null
    var gameStarted: Boolean = false

    fun gameBegin(userInput: Int) {
        gameGeneration.generateNums(userInput)
        gameStarted = true

    }
    fun submitSequence(userInput: List<Int>): Boolean {
        gameGeneration.setUserInput(userInput)

        val correct =
            gameGeneration.determineIfSequenceCorrect(userInput)

        val targetSequence =
            gameGeneration.getPredefinedNums()

        logging.addLog(
            sequenceLength = targetSequence.size,
            userInput = userInput,
            targetSequence = targetSequence,
            correct = correct,
            timestamp = System.currentTimeMillis()
        )

        return correct
    }
    fun getGeneratedNums(): List<Int> {
        return gameGeneration.getPredefinedNums()
    }
    fun getCurrentNumber(index: Int): Int {
        return getGeneratedNums()[index]
    }
    fun getLogs(): List<Log> {
        return logging.getLogs()
    }
    fun getTotalAttempts(): Int {
        return gameGeneration.getTotalGames()
    }

    fun getCorrectAttempts(): Int {
        return gameGeneration.getCorrectGames()
    }

    fun getAccuracy(): Float {
        return gameGeneration.determineAccuracy()
    }
}