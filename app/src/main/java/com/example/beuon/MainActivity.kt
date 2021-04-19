package com.example.beuon

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.beuon.roleMenu.ui.TutorMainActivity


class MainActivity : AppCompatActivity() {

    var _usernameText: TextView? = null
    private var username : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.beuon.R.layout.activity_main)
        this.getRoleUser()
    }

    fun getUsername() : String {
        val i = intent
        username = i.getStringExtra("username")!!
        _usernameText = findViewById(R.id.text_username) as TextView
        _usernameText!!.setText("Welcome "+username.toString())
        return username.toString()
    }

    private fun getRoleUser(){
        if(getUsername().equals("studenttest")){
            val i = Intent(this, MainActivity::class.java)
            i.putExtra("username", username)
            startActivity(i)
        }else if(getUsername().equals("tutortest")){
            val i = Intent(this, TutorMainActivity::class.java)
            i.putExtra("username", username)
            startActivity(i)
        }else if(getUsername().equals("parenttest")){
            val i = Intent(this, MainActivity::class.java)
            i.putExtra("username", username)
            startActivity(i)
        }
        val i = Intent(this, MainActivity::class.java)
        i.putExtra("username", username)
        startActivity(i)
    }

}
