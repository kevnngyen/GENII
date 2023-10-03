package com.example.geniiproject

import Deck
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PlayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlayFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_play, container, false)


        var playB = view.findViewById<Button>(R.id.startGame)
        var ErrorMsg = view.findViewById<TextView>(R.id.warning)

        playB.setOnClickListener {

            if(Deck.cache.isEmpty()){

                ErrorMsg.setText("Must have atleast one card created before playing")

            }
            else{

                val intent = Intent(requireContext(), GamePlay::class.java)
                /** Important line to help switch between Activity */

                startActivity(intent)

            }


        }



        return view
    }

}