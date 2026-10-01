package com.example.zanoon_rapidrecall.controller

import androidx.lifecycle.ViewModel
import com.example.zanoon_rapidrecall.model.GameGeneration

class MainViewModel : ViewModel() {

    private val gameGeneration = GameGeneration()
    private var preGeneratedNums: List<Int> = emptyList()
    var currentNumber: Int? = null
    var gameStarted: Boolean = false

    fun gameBegin(userInput: Int) {
        preGeneratedNums = gameGeneration.generateNums(userInput)
        currentNumber = gameGeneration.getCurrentNumber()
        gameStarted = true

    }
    fun submitAnswer(userInput: Int): Boolean {

        val correct = gameGeneration.determineIfNumCorrect(
            gameGeneration.getCurrentNumber(),
            userInput
        )
        gameGeneration.setUserInput(userInput)
        gameGeneration.updateIndex()
        return correct
    }
}