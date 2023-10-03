package com.example.geniiproject

class Card(QuestionInput: String) {

    private var Question: String = QuestionInput

    /** This is used to create the cards for when playuing the game**/

    fun getQuestion(): String {
        return this.Question
    }


}