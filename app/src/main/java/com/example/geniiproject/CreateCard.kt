package com.example.geniiproject

import Deck
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreateCard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)
        supportActionBar!!.hide() //hides the action bar



        /*Traverse back to home*/
        val backButton = findViewById<ImageButton>(R.id.goBack)

        backButton.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        }

        /*creates the card*/
        val createButton = findViewById<Button>(R.id.Create_Button)

        createButton.setOnClickListener {

            var qInput = findViewById<EditText>(R.id.QuestionInput)
            var QuestionInput: String = qInput.text.toString()

            var aInput = findViewById<EditText>(R.id.AnswerInput)
            var AnswerInput: String = aInput.text.toString()

            if (QuestionInput.length <= 0) {
                qInput.error = "Please input a title"
                return@setOnClickListener
            }

            if (AnswerInput.length <= 0) { //if the Answer input is empty, display error message
                aInput.error = "Please input an answer"
                return@setOnClickListener
            }

            else{


                if(Deck.cache.containsKey(QuestionInput)){
                    // no duplicate question can be allowed with the same title
                    qInput.error = "There is an already existing question with the same title"
                    return@setOnClickListener

                }

                Deck.cache.put(QuestionInput,AnswerInput)

                val intent = Intent(this, MainActivity::class.java) // after the card has been inputted into the list return home.
                startActivity(intent)
                val toast = Toast.makeText(applicationContext, "Card has been created", Toast.LENGTH_SHORT)
                toast.show()



            }

            Database(this).saveData()



        }

    }
}