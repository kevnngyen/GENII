package com.project.geniiproject

import Deck
import MyAdapter
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        Database(requireActivity()).saveData()

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        /**LIST VIEW SECTION**/
        val listView = view.findViewById<ListView>(R.id.listview)

        val InitialList: ArrayList<String> = ArrayList<String>(Deck.cache.keys)
        val cardList = ArrayList<Card>()

        for(i in InitialList.indices){
            cardList.add(Card(InitialList[i]))
        }

        val arrayAdapter: ArrayAdapter<Card> = MyAdapter(requireContext(),R.layout.list_row, cardList)
        listView.adapter = arrayAdapter

        //when the item is clicked
        listView.onItemClickListener =
            OnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->

                var dialogBuilder = AlertDialog.Builder(activity)

                //convert the popup layout to be objects
                var popupView = layoutInflater.inflate(R.layout.popup, null)
                dialogBuilder.setView(popupView)

                //Builds the popup and shows it
                var dialog = dialogBuilder.create()
                dialog.show()

                /**This is to get the Answer and Question title of the card**/
                val answerInput = popupView.findViewById<EditText>(R.id.AnswerInput2);
                val questionTitle = popupView.findViewById<TextView>(R.id.Question1)
                //To get the string of the clicked item
                var p = cardList.get(position).getQuestion()//gets the answer
                answerInput.setText(Deck.cache.get(p))
                questionTitle.setText(cardList.get(position).getQuestion())

                //button on the popup
                val backButton = popupView.findViewById<ImageButton>(R.id.goBack)
                val saveButton = popupView.findViewById<Button>(R.id.Save)
                val deleteButton = popupView.findViewById<Button>(R.id.delete)

                /**BACK BUTTON**/
                backButton.setOnClickListener{ dialog.hide() } //Hides the popup

                /**SAVE BUTTON**/
                saveButton.setOnClickListener {

                    if(answerInput.length() <= 0){
                        answerInput.setError("Please input an answer")
                    }
                    else{ //replaces the old answer with the new
                        var newAnswer: String = answerInput.text.toString()
                        Deck.cache.replace(cardList.get(position).getQuestion(), newAnswer)
                        Toast.makeText(activity, "New answer has been save", Toast.LENGTH_SHORT).show()
                        Database(requireActivity()).saveData()
                        dialog.hide()

                    }

                }

                /**DELETE BUTTON**/
                deleteButton.setOnClickListener{
                    dialog.hide()//Hides the previous popup
                    popupView = layoutInflater.inflate(R.layout.are_u_sure_popup, null)
                    dialogBuilder.setView(popupView)
                    dialog = dialogBuilder.create()
                    dialog.show() //shows the new popup

                    //Buttons inside the confirmation popup
                    val deleteButton2 = popupView.findViewById<Button>(R.id.delete)// The delete button inside the
                    val backButton2 = popupView.findViewById<ImageButton>(R.id.goBack)

                    //COMPLETELY DELETES THE CARD
                    deleteButton2.setOnClickListener {

                        Deck.cache.remove(cardList.get(position).getQuestion()) //Removes the card from the deck
                        cardList.remove(cardList.get(position)) //Removes the card from the cardList tracker

                        dialog.hide()
                        Database(requireActivity()).saveData()

                        //restarts the activity to reload the listView
                        val intent = Intent(activity, MainActivity::class.java)
                        startActivity(intent)

                    }
                    //Goes back to the previous popup
                    backButton2.setOnClickListener {
                        dialog.hide()
                    }

                }


            }
        /***************************/




        return view
    }




}



