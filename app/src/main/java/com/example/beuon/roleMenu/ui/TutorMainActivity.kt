package com.example.beuon.roleMenu.ui

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.beuon.R
import com.example.beuon.roleMenu.ui.courses.CourseFragment


class TutorMainActivity : AppCompatActivity() {

    var _usernameText: TextView? = null
    private var username : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutor_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_course, R.id.navigation_profile, R.id.navigation_setting))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        sendData()
    }

    fun getUsername() : String {
        val i = intent
        username = i.getStringExtra("username")!!
        return username
    }

    private fun sendData() {
        val bundle = Bundle()
        bundle.putString("username", getUsername()) // data that you want to send
        val fragment = CourseFragment() // Fragment that you want to call
        fragment.setArguments(bundle)
        val fts: FragmentTransaction = supportFragmentManager.beginTransaction()
        fts.replace(R.id.content_id, fragment)
        fts.commit()
    }
}