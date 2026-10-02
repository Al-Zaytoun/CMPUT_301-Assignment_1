package com.example.zanoon_rapidrecall.model

class GameGeneration {
    /*
    * Class GameGeneration
    * Purpose: Acts as the "Model" in that it handles the data-level logic of the app. its been designed as to not be invoked from any of the view files
    *          but only of being invoked through the controller class
    * Design Rationale: Intended for it to hold all the information that the app would need for its functionality
    *
    * */
    private var randomNums = mutableListOf<Int>()
    private var userNums = mutableListOf<Int>()
    private var correctGames: Int = 0
    private var totalGames: Int = 0

    fun generateNums(userInput: Int): List<Int> {
        randomNums.clear()
        for (i in 0 until userInput) {
            // Generate random int (from 0 to 9)
            val randomInt = (0..9).random()
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

    fun determineIfSequenceCorrect(userNums: List<Int>): Boolean {
        totalGames++
        if (randomNums.equals(userNums)) {
            correctGames++
            return true
        }
        return false
    }
    fun getCorrectGames(): Int {
        return correctGames
    }
    fun getTotalGames(): Int {
        return totalGames
    }


    fun determineAccuracy(): Float{
        if (totalGames == 0) {
            return 0f
        }
        return (correctGames.toFloat() / totalGames.toFloat()) * 100
    }
}
