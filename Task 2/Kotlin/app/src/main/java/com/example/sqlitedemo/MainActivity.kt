package com.example.sqlitedemo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var et_name: EditText
    private lateinit var et_age: EditText
    private lateinit var btn_add: Button
    private lateinit var btn_view: Button
    private lateinit var btn_update: Button
    private lateinit var btn_delete: Button
    private lateinit var tv_result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize DatabaseHelper
        databaseHelper = DatabaseHelper(this)

        // Initialize UI components
        et_name = findViewById(R.id.et_name)
        et_age = findViewById(R.id.et_age)
        btn_add = findViewById(R.id.btn_add)
        btn_view = findViewById(R.id.btn_view)
        btn_update = findViewById(R.id.btn_update)
        btn_delete = findViewById(R.id.btn_delete)
        tv_result = findViewById(R.id.tv_result)

        // Set button click listeners
        btn_add.setOnClickListener {
            addUser()
        }
        btn_view.setOnClickListener {
            viewUsers()
        }
        btn_update.setOnClickListener {
            updateUser()
        }
        btn_delete.setOnClickListener {
            deleteUser()
        }
    }

    private fun addUser() {
        val name = et_name.text.toString()
        val age = et_age.text.toString().toIntOrNull()

        if (name.isNotEmpty() && age != null) {
            val success = databaseHelper.addUser(name, age)
            if (success) {
                Toast.makeText(this, "User added successfully!", Toast.LENGTH_SHORT).show()
                et_name.text.clear()
                et_age.text.clear()
            }
            else {
                Toast.makeText(this, "Failed to add user", Toast.LENGTH_SHORT).show()
            }
        }
        else {
            Toast.makeText(this, "Please enter a valid name and age", Toast.LENGTH_SHORT).show()
        }
    }

    private fun viewUsers() {
        val users = databaseHelper.getAllUsers()
        tv_result.text = if (users.isNotEmpty()) {
            users.joinToString("\n")
        }
        else {
            "No users found"
        }
    }

    private fun updateUser() {
        val name = et_name.text.toString()
        val age = et_age.text.toString().toIntOrNull()

        if (databaseHelper.updateUser(name, age)) {
            Toast.makeText(this, "User updated successfully!", Toast.LENGTH_SHORT).show()
            viewUsers() // Update displayed users
        } else {
            Toast.makeText(this, "Failed to update user", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteUser() {
        val name = et_name.text.toString()
        if (databaseHelper.deleteUser(name)) {
            Toast.makeText(this, "User deleted successfully!", Toast.LENGTH_SHORT).show()
            viewUsers() // Update displayed users
            et_name.text.clear()
            et_age.text.clear()
        } else {
            Toast.makeText(this, "Failed to delete user", Toast.LENGTH_SHORT).show()
        }
    }
}