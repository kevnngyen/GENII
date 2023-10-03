package com.example.geniiproject

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationBarView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide() //hides the action bar

        val addButton = findViewById<FloatingActionButton>(R.id.addButton)

        val navigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation) // Initialize the BottomNavigationView

        Database(this).loadData()
        /** Important line to help switch between fragments */
        supportFragmentManager.beginTransaction().replace(R.id.body_container, HomeFragment()).commit()

        navigationView.setSelectedItemId(R.id.home) //Default sets the fragment to home

        navigationView.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item: MenuItem ->
            var fragment: Fragment? = null
            if (item.itemId == R.id.home) {
                fragment = HomeFragment()
            }
            if (item.itemId == R.id.play) {
                fragment = PlayFragment()
            }
            supportFragmentManager.beginTransaction().replace(R.id.body_container, fragment!!).commit()
            true
        })


        addButton.setOnClickListener {

            val intent = Intent(this, CreateCard::class.java)
            /** Important line to help switch between Activity */

            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)


        }



    }





}