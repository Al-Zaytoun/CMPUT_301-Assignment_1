package com.example.zanoon_rapidrecall.model

class GameGeneration {
    private var randomNums = mutableListOf<Int>()
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

    fun getPredefinedNums(): List<Int> {
        return randomNums
    }

    fun setUserInput(nums: List<Int>) {
        userNums.clear()
        userNums.addAll(nums)
    }

    fun getUserInput() : List<Int>{
        return userNums
    }

    fun setTime() {
        timestamp = System.currentTimeMillis()
    }

    fun determineIfSequenceCorrect(userNums: List<Int>): Boolean {
        totalGames++
        if (randomNums.equals(userNums)) {
            correctGames++
            return true
        }
        return false
    }

    fun determineAccuracy(): Float{
        return (correctGames.toFloat() / totalGames.toFloat())
    }
}
