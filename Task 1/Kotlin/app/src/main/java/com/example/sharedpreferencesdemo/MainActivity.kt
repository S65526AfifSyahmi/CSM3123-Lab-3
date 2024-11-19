package com.example.sharedpreferencesdemo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var tv_greeting: TextView
    private lateinit var et_name: EditText
    private lateinit var et_age: EditText
    private lateinit var btn_save: Button
    private lateinit var btn_load: Button
    private lateinit var btn_clear: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components
        tv_greeting = findViewById(R.id.tv_greeting)
        et_name = findViewById(R.id.et_name)
        et_age = findViewById(R.id.et_age)
        btn_save = findViewById(R.id.btn_save)
        btn_load = findViewById(R.id.btn_load)
        btn_clear = findViewById(R.id.btn_clear)

        // Set up button click listeners
        btn_save.setOnClickListener {
            saveName()
        }

        btn_load.setOnClickListener {
            loadName()
        }

        btn_clear.setOnClickListener {
            clearName()
        }
    }

    private fun saveName() {
        // Validate the input
        if (et_name.text.toString().isEmpty()) {
            et_name.error = "Please enter your name"
            return
        }
        if (et_age.text.toString().isEmpty()) {
            et_age.error = "Please enter your age"
            return
        }

        // Get the SharedPreferences objects
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)

        // Open the editor to write SharedPreferences
        val editor = sharedPreferences.edit()

        // Get the name from EditText and save it with a key
        val name = et_name.text.toString()
        editor.putString("userName", name)
        val age = et_age.text.toString()
        editor.putString("userAge", age)

        // Apply changes to save the data
        editor.apply()

        // Show a confirmation message
        et_name.text.clear()
        et_age.text.clear()
        tv_greeting.text = "Data saved!"
    }

    private fun loadName() {
        // Get the SharedPreferences objects
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)

        // Retrieve the saved name using the key
        val savedName = sharedPreferences.getString("userName", null)
        val savedAge = sharedPreferences.getString("userAge", null)

        // Display the saved name in the TextView
        if (savedName != null && savedAge != null) {
            tv_greeting.text = "Hello, $savedName! Your age is $savedAge."
        }
        else {
            tv_greeting.text = "No data saved."
        }
    }

    private fun clearName() {
        // Get the SharedPreferences objects
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)

        // Open the editor to write SharedPreferences
        val editor = sharedPreferences.edit()

        // Clear the saved name using the key
        editor.remove("userName")
        editor.remove("userAge")

        // Apply changes to save the data
        editor.apply()

        // Clear the EditText and TextView
        et_name.text.clear()
        et_age.text.clear()
        tv_greeting.text = "Data cleared."
    }
}