package com.example.beuon

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    var _usernameText: TextView? = null
    private var roleUser: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.beuon.R.layout.activity_main)
        roleUser = this.getRoleUser()
    }

    fun getUsername() : String {
        val i = intent
        val username = i.getStringExtra("username")
        _usernameText = findViewById(R.id.text_username) as TextView
        _usernameText!!.setText("Welcome "+username.toString())
        return username.toString()
    }

    private fun getRoleUser() : String{
        if(getUsername().equals("studenttest")){
            return "student"
        }else if(getUsername().equals("tutortest")){
            return "tutor"
        }else if(getUsername().equals("parenttest")){
            return "parents"
        }
        return ""
    }

}
