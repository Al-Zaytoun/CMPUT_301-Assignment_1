package com.example.zanoon_rapidrecall.model

class GameGeneration {
    private var randomNums = mutableListOf<Int>()
    private var randomIdx = 0
    private var userNums = mutableListOf<Int>()
    private var correctGames: Int = 0
    private var totalGames: Int = 0
    private var timestamp: Long = 0L

    fun generateNums(userInput: Int): List<Int> {
        randomNums.clear()
        for (i in 0 until userInput) {
            // Generate random int (from 1 to 50)
            val randomInt = (1..50).random()
            randomNums.add(randomInt)
        }
        return randomNums
    }

    fun getCurrentNumber(): Int {
        return randomNums[randomIdx]
    }

    fun updateIndex() {
        randomIdx++
    }

    fun setUserInput(num: Int) {
        userNums.add(num)
    }

    fun getUserInput() : List<Int>{
        return userNums
    }

    fun setTime() {
        timestamp = System.currentTimeMillis()
    }

    fun determineIfNumCorrect(randomNum: Int, userNum: Int): Boolean {
        if (randomNum.equals(userNum)) {
            correctGames++
            return true
        }
        totalGames++
        return false
    }

    fun determineAccuracy(): Float{
        return (correctGames.toFloat() / totalGames.toFloat())
    }
}
