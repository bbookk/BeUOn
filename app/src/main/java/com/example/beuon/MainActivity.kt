package com.example.beuon

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    var _usernameText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.beuon.R.layout.activity_main)
        getUsername()
    }

    fun getUsername(){
        val i = intent
        val username = i.getStringExtra("username")
        _usernameText = findViewById(R.id.text_username) as TextView
        _usernameText!!.setText("Welcome "+username.toString())
    }

}
