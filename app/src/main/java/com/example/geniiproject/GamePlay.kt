package com.example.geniiproject

import Deck
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class GamePlay : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gameplay)
        supportActionBar!!.hide() //hides the action bar

        /** Variables **/
        val playlist: ArrayList<String> = ArrayList(Deck.cache.keys) //List to keep track of which card has been used
        val header = findViewById<TextView>(R.id.header) //Displays the Header Question/Answer
        val display = findViewById<TextView>(R.id.qOrA) //Reveals the Answer/Question
        val tip = findViewById<TextView>(R.id.tip) //Instructions to what the user can do
        val cardLayout = findViewById<LinearLayout>(R.id.card)
        val homeButton = findViewById<ImageButton>(R.id.homeButton)
        val continueButton = findViewById<Button>(R.id.continueGame)
        val counterDisplay = findViewById<TextView>(R.id.counterHeader)
        var counter: Int = 1

        /*Selects the card randomly*/
        var randomIndex: Int
        randomIndex = Random.nextInt(playlist.size)

        /*Preset the very first card*/
        var reveal = false
        header.setText("Question")
        display.setText(Deck.cache.get(playlist.get(randomIndex)))
        tip.setText("Click anywhere on the card to show the Answer")
        counterDisplay.setText("$counter/${Deck.cache.size}")



        /*Game play*/
        cardLayout.setOnClickListener {

            if(reveal == true){ //Flip the card back to the question

                reveal = false

                header.setText("Question")
                header.setTextColor(Color.parseColor("#CB0000"))

                display.setText(playlist.get(randomIndex))
                display.setTextColor(Color.parseColor("#CB0000"))

                tip.setText("Click anywhere on the card to show the Answer")

            }

            else if(reveal == false){ //Reveals the card answer

                reveal = true

                header.setText("Answer")
                header.setTextColor(Color.parseColor("#FF8FC692"))

                display.setText(Deck.cache.get(playlist.get(randomIndex)))
                display.setTextColor(Color.parseColor("#FF8FC692"))

                tip.setText("Click anywhere on the card to show the Question")

            }

        }

        continueButton.setOnClickListener {

            playlist.remove(playlist.get(randomIndex))//removes the card from the list

            if(playlist.isEmpty()){ //returns home if call cards has been played
                homeButton.callOnClick()
                return@setOnClickListener
            }
            //Plays to a new card
            counter++
            counterDisplay.setText("$counter/${Deck.cache.size}")
            randomIndex = Random.nextInt(playlist.size)
            reveal = false
            header.setText("Question")
            display.setText(Deck.cache.get(playlist.get(randomIndex)))
            tip.setText("Click anywhere on the card to show the Answer")
            display.setTextColor(Color.parseColor("#CB0000"))
            header.setTextColor(Color.parseColor("#CB0000"))

        }

        homeButton.setOnClickListener {//Goes home page
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }


    }
}