package com.example.beuon

import android.app.Activity
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.*
import com.example.beuon.roleMenu.ui.courses.CourseFragment
import java.text.SimpleDateFormat
import java.util.*

class CourseActivity : AppCompatActivity() {

    var _nameText: EditText? = null
    var _descText: EditText? = null
    var _placeText : EditText? = null
    var _priceText : EditText? = null
    var _dateText: EditText? = null
    var _createBtn: Button? = null
    var type : String = ""

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.beuon.R.layout.activity_create_course)

        _nameText = findViewById(R.id.input_name) as EditText
        _descText = findViewById(R.id.input_desc) as EditText
        _placeText = findViewById(R.id.input_place) as EditText
        _priceText = findViewById(R.id.input_price) as EditText
        _dateText = findViewById(R.id.input_date) as EditText
        _createBtn = findViewById(R.id.create_btn) as Button

        _createBtn!!.setOnClickListener { createCourse() }

        _dateText!!.transformIntoDatePicker(this, "dd/MM/yyyy")
        _dateText!!.transformIntoDatePicker(this, "dd/MM/yyyy", Date())
    }

    fun EditText.transformIntoDatePicker(context: Context, format: String, maxDate: Date? = null) {
        isFocusableInTouchMode = false
        isClickable = true
        isFocusable = false

        val myCalendar = Calendar.getInstance()
        val datePickerOnDataSetListener =
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    myCalendar.set(Calendar.YEAR, year)
                    myCalendar.set(Calendar.MONTH, monthOfYear)
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    val sdf = SimpleDateFormat(format, Locale.UK)
                    setText(sdf.format(myCalendar.time))
                }

        setOnClickListener {
            DatePickerDialog(
                    context, datePickerOnDataSetListener, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)
            ).run {
                maxDate?.time?.also { datePicker.maxDate = it }
                show()
            }
        }
    }

    fun createCourse() {
        Log.d(TAG, "Create")

        _createBtn!!.isEnabled = false

        if(validate()){

        val progressDialog = ProgressDialog(this@CourseActivity,
                com.example.beuon.R.style.AppTheme_Dark_Dialog)
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Creating Course...")
        progressDialog.show()

        val name = _nameText!!.text.toString()
        val desc = _descText!!.text.toString()
        val place = _placeText!!.text.toString()
        val price = _priceText!!.text.toString()
        val dateTime = _dateText!!.text.toString()

        android.os.Handler().postDelayed(
                {
                    onCreateCourseSuccess()
                    progressDialog.dismiss()
                }, 3000)
        }
    }


    fun onCreateCourseSuccess() {
        _createBtn!!.isEnabled = true
        setResult(Activity.RESULT_OK, null)
        val bundle = Bundle()
        bundle.putString("data", "Data you want to send")
        val obj = CourseFragment()
        obj.setArguments(bundle)
//        startActivity(Intent(getApplicationContext(), CourseFragment::class.java))
//        finish()
    }


    fun validate(): Boolean {
        var valid = true

        val name = _nameText!!.text.toString()
        val place = _placeText!!.text.toString()
        val price = _priceText!!.text.toString()
        val dateTime = _dateText!!.text.toString()

        if (name.isEmpty()) {
            _nameText!!.error = "Enter Name of Title"
            valid = false
        } else {
            _nameText!!.error = null
        }

        if (place.isEmpty()) {
            _placeText!!.error = "Enter a place"
            valid = false
        } else {
            _placeText!!.error = null
        }

        if (price.isEmpty()) {
            _priceText!!.error = "Enter Price"
            valid = false
        } else {
            _priceText!!.error = null
        }

        if (dateTime.isEmpty()) {
            _dateText!!.error = "Date Cannot be null"
            valid = false
        } else {
            _dateText!!.error = null
        }

        return valid
    }

    fun onRadioButtonClicked(view: View){
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_onsite ->
                    if (checked) {
                        type = "onsite"
                    }
                R.id.radio_online ->
                    if (checked) {
                        type = "online"
                    }
            }
        }
        type =  ""
    }

    companion object {
        private val TAG = "CourseActivity"
    }
}